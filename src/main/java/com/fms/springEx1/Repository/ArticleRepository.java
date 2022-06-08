package com.fms.springEx1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fms.springEx1.Entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	/**
	 * Read articles by description key word
	 * @param description
	 * @return
	 */
	public List<Article> findByDescription(String description);
	
	/**
	 * Read Article by part of key word
	 * @param keyWord
	 * @return
	 */
	public List<Article> findByDescriptionContains(String keyWord);
	
	/**
	 * Read articles by description key word
	 * @param brand
	 * @return
	 */
	public List<Article> findByBrand(String brand);
	
	/**
	 * Read Article by part of key word
	 * @param keyWord
	 * @return
	 */
	public List<Article> findByBrandContains(String keyWord);
	
	/**
	 * 
	 * @param brand
	 * @param price
	 * @return
	 */
	public Article findByBrandAndPrice(String brand, Double price);
	
	/**
	 * find articles By Brand And Price Greater Than price 
	 * @param brand
	 * @param price
	 * @return
	 */
	public List<Article> findByBrandAndPriceGreaterThan(String brand, Double price);
	
	/**
	 * Read articles with brand and price greater than y using HQL query
	 * @param brand
	 * @param price
	 * @return
	 */
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> findByBrandAndPriceMin(@Param("x") String brand, @Param("y")Double price);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> findByCategoryId(Long id);
}
