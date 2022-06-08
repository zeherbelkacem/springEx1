package com.fms.springEx1.Service;

import java.util.List;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;

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
	
}
