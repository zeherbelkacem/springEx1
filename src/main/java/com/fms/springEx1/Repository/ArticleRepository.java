package com.fms.springEx1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.springEx1.Entities.Article;

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
	 * @param keyWord
	 * @param pageable
	 * @return
	 */
	public Page<Article> findByBrandContainsAndCategoryName(String keyWord, String catName, Pageable pageable);
	
	/**
	 * 
	 * @param keyWord
	 * @param pageable
	 * @return
	 */
	public Page<Article> findByCategoryName(String keyWord, Pageable pageable);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Article> findByCategoryName(String name);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> findByCategoryId(Long id);

	public Page<Article> readByBrandContainsAndCategoryName(String keyWord, String catName, Pageable pageable);

	public Page<Article> findByDescriptionContainsAndCategoryName(String keyWord, String catName, Pageable pageable);
	
//	@Modifying
//    @Query("UPDATE Article a SET a.description = :art.description, a.brand = :art.brand WHERE a.id = :aId")
//    Article updateArticleQ(@Param("art") Article art, @Param("aId") long aId);
	
}
