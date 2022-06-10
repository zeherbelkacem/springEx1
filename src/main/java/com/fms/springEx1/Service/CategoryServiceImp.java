package com.fms.springEx1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Entities.CategoryEnum;
import com.fms.springEx1.Repository.CategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService{

	/*
	 * Repositories Dependency Injections
	 */
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
	public Category getCategoryByName(CategoryEnum catName) {
		return categoryRepository.findByName(catName);
	}

}
