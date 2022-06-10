package com.fms.springEx1.Service;

import java.util.List;
import java.util.Optional;

import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Entities.CategoryEnum;

public interface ICategoryService {

	/*
	 *********************** CRUD methods ***************************
	 * 
	 */
	
	/**
	 * 
	 * @param article
	 * @return
	 */
	public Category saveCategory(Category category);

	/**
	 * 
	 * @return
	 */
	public List<Category> readAllCategories();

	/**
	 * 
	 * @param idCat
	 * @return
	 */
	public Category getCategoryById(Long idCat);

	public Category getCategoryByName(CategoryEnum catName);
	
}