package com.fms.springEx1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Repository.ArticleRepository;


@Service
public class ArticleServiceImpl implements IArticleService {

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
		return articleRepository.findById(id).get();
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
				currentArticle
				.setDescription(article.getDescription());
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

	@Override
	public List<Article> readArticlesPriceASC() {
		return articleRepository.readArticlesByPriceAsc();
	}

	@Override
	public List<Article> readArticlesPriceDESC() {
		return articleRepository.readArticlesByPriceDesc();
	}

}
