package com.fms.springEx1.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Exceptions.ErrorCode;
import com.fms.springEx1.Exceptions.NotFoundEntityException;
import com.fms.springEx1.Repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {

	private Map<Long, Article> cartMap = new HashMap<Long, Article>();
	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> realAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article readById(Long id) {
		return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Article not found using this id: "+id));
	}

	@Override
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public void deleteArticleById(Long id) {
		articleRepository.deleteById(id);
	}

	@Override
	public Article updateArticle(Long id, Article article) {
		Optional<Article> art = articleRepository.findById(id);

		if (art.isPresent()) {
			Article currentArticle = art.get();

			if (article.getDescription() != null) {
				currentArticle.setDescription(article.getDescription());
			}
			if (article.getBrand() != null) {
				currentArticle.setBrand(article.getBrand());
			}
			if (article.getPrice() != null) {
				currentArticle.setPrice(article.getPrice());
			}

			return (articleRepository.save(currentArticle));
		} else {
			return null;
		}

	}

	/**
	 * 
	 */
	@Override
	public List<Article> readArticleByCatgoryId(Long id) {
		return articleRepository.findByCategoryId(id);
	}

	/**
	 * 
	 */
	@Override
	public List<Article> readArticleByCatgoryName(String name) {
		return articleRepository.findByCategoryName(name);
	}

	/**
	 * 
	 */
	@Override
	public Page<Article> readArticlesPageByPage(int page, int size) {
		return articleRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Article> findByPageByPageAndKeyWord(String keyWord, Pageable pageable) {
		if(articleRepository.findByBrandContains(keyWord, pageable).isEmpty())
			throw new NotFoundEntityException("Articles not found", ErrorCode.ARTICLES_NOT_FOUND_FOR_BRAND);
		return articleRepository.findByBrandContains(keyWord, pageable);
	}

	@Override
	public void addArticleToCart(Long idArticle, int quantity) {
		// gestion du panier daans metier

		/** verifier l'existanec de l'ARTICLE ID */
		Article article = articleRepository.findById(idArticle).get();

		if (article != null) {
			//if (cartMap.containsKey(idArticle)) {// add the same product -> quantity incremented
				//int qt = getMyCart().get(idArticle).getQuantity() + quantity;
				article.setQuantity(quantity);
				cartMap.put(article.getId(), article);

//			} else { // new product in the bucket (first time){
//				
//				article.setQuantity(1);
//				cartMap.put(article.getId(), article);
//			}
		}

	}

	@Override
	public Map<Long, Article> getMyCart() {
		return cartMap;
	}

	@Override
	public Page<Article> findByPageByPageAndCategoryName(String name, Pageable pageable) {
		return articleRepository.findByCategoryName(name, pageable);
	}

	@Override
	public Double getTotalSum() {
		Double productTotalPrice = 0.0;
		Double TotalPrice = 0.0;
		/* Start to fill the table body with the selected training */
		for (Entry<Long, Article> entry : cartMap.entrySet()) {
			productTotalPrice = entry.getValue().getPrice() * entry.getValue().getQuantity();

			TotalPrice += productTotalPrice;
		}
		return TotalPrice;
	}

	@Override
	public void removeArticleFromCart(Long idArticle) {
//		if (cartMap.get(idArticle).getQuantity() > 1)
//			cartMap.get(idArticle).setQuantity(cartMap.get(idArticle).getQuantity() - 1);
//		else
			cartMap.remove(idArticle);
	}

	@Override
	public Page<Article> readByBrandContainsAndCategoryName(String keyWord, String catName, Pageable pageable) {
		return articleRepository.readByBrandContainsAndCategoryName(keyWord, catName, pageable);
	}

	@Override
	public Page<Article> readByDescriptionContainsAndCategoryName(String keyWord, String catName, Pageable pageable) {
		return articleRepository.findByDescriptionContainsAndCategoryName( keyWord,  catName,  pageable);
	}

}
