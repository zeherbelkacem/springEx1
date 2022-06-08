package com.fms.springEx1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	
	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

}
