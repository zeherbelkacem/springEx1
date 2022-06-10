package com.fms.springEx1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.CategoryEnum;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<Article> findByBrandContains(String keyWord, Pageable pageable);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Article> findByCategoryName(CategoryEnum name);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> findByCategoryId(Long id);
	
}
