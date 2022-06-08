package com.fms.springEx1.Service;

import java.util.List;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;

public interface IArticleService {
	
	/*
	 *********************** CRUD methods ***************************
	 * 
	 */
	/**
	 * 
	 * @return
	 */
	public List<Article> realAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Article readById(Long id);
	
	/**
	 * 
	 * @param article
	 * @return
	 */
	public Article saveArticle(Article article);
	
	/**
	 * 
	 * @param id
	 */
	public  void deleteArticleById(Long id);
	
	/**
	 * 
	 * @param id
	 * @param article
	 * @return
	 */
	public Article updateArticle(Long id, Article article);
	
	
	/*
	 * Some tests using some columns parameters
	 */
	
	/**
	 * 
	 * @return
	 */
	public List<Article> readArticlesPriceASC();
	
	/**
	 * 
	 * @return
	 */
	public List<Article> readArticlesPriceDESC();

}
