package com.fms.springEx1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Exceptions.ErrorCode;
import com.fms.springEx1.Exceptions.NotFoundEntityException;
import com.fms.springEx1.Repository.CategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService{

	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> readAllCategories() {
		return categoryRepository.findAll();
		
	}

	@Override
	public Category getCategoryById(Long idCat) {
		return categoryRepository.findById(idCat).get();
	}

	@Override
	public Category getCategoryByName(String catName) {
		return categoryRepository.findByName(catName);
	}

	@Override
	public void deleteCategory(long id) {
		if (!articleService.readArticleByCatgoryId(id).isEmpty()) {
			throw new NotFoundEntityException("Articles in use, you can't delete this category", ErrorCode.ARTICLE_IN_USE);
		}
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Page<Category> categoriesPageByPage(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

}
