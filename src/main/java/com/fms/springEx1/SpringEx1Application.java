package com.fms.springEx1;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Repository.ArticleRepository;
import com.fms.springEx1.Repository.CategoryRepository;


@SpringBootApplication
public class SpringEx1Application implements CommandLineRunner{
	
	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringEx1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Save some categories
		 */
		categoryRepository.save(new Category("PC"));
		categoryRepository.save(new Category("SMARTPHONE"));
		categoryRepository.save(new Category("TABLET"));
		categoryRepository.save(new Category("HARDWARE"));
		
		
		/*
		 * Save some Articles
		 */
		articleRepository.save(new Article(null, "S10", "Samsung", (double) 350));
		articleRepository.save(new Article(null, "S7", "Samsung", 300.));
		articleRepository.save(new Article(null, "MI10", "Xiomi", 250D));
		articleRepository.save(new Article(null, "GalaxyTab", "Samsung", (double) 150));
		articleRepository.save(new Article(null, "EliteBook 16G", "HP", (double) 1350));
		articleRepository.save(new Article(null, "Ipad", "Apple", (double) 100));
		
		/*
		 * Some requests TEST
		 */
		System.out.println("-------------------------Read all articles------------------");
		articleRepository.findAll().forEach(a->{
			System.out.println(a);
		});
		//OR
//		for (Article article : articleRepository.findAll()) 
//			System.out.println(article);
		
		System.out.println("------------------------Read article by Id------------------");
		System.out.println(articleRepository.findById(2L));
		
		System.out.println("----------------------Delete article by Id------------------");
//		if(articleRepository.findById(2L).get() != null)
//			articleRepository.deleteById(2L);
		
		System.out.println("----------------------Update article ------------------------");
		Optional<Article> art = articleRepository.findById(2L);
		if (art.isPresent()) {
			Article currentArticle = art.get();
			currentArticle.setPrice(305D);;
			System.out.println(articleRepository.save(currentArticle));;

		} else {
			System.out.println("Article not found");
		}
		
		System.out.println("------------------Read Article with HQL -------------------");
		articleRepository.findByBrandAndPriceMin("Samsung", 300D).forEach(a->{
			System.out.println(a);
		});
	}

	
}
