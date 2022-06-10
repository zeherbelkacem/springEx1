package com.fms.springEx1.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Entities.CategoryEnum;

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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> readArticleByCatgoryId(Long id);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Article> readArticleByCatgoryName(CategoryEnum name);
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> readArticlesPageByPage(int page, int size);
	
	/**
	 * 
	 * @param keyWord
	 * @param pageable
	 * @return
	 */
	public Page<Article> findByPageByPageAndKeyWord(String keyWord, Pageable pageable);
	
}