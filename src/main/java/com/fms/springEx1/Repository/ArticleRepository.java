package com.fms.springEx1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fms.springEx1.Entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	/**
	 * 
	 * @return
	 */
	@Query("SELECT art FROM Article as art ORDER BY art.price ASC")
	public List<Article> readArticlesByPriceAsc();
	
	/**
	 * 
	 * @return
	 */
	@Query("SELECT art FROM Article as art ORDER BY art.price DESC")
	public List<Article> readArticlesByPriceDesc();
	
}
