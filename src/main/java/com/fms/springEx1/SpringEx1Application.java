package com.fms.springEx1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Service.IArticleService;
import com.fms.springEx1.Service.ICategoryService;


@SpringBootApplication
public class SpringEx1Application implements CommandLineRunner{
	
	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEx1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Save some categories
		 */
		categoryService.saveCategory(new Category("PC"));
		categoryService.saveCategory(new Category("SMARTPHONE"));
		categoryService.saveCategory(new Category("TABLET"));
		categoryService.saveCategory(new Category("HARDWARE"));
		
		
		/*
		 * Save some Articles
		 */
		articleService.saveArticle(new Article(null, "S10", "Samsung", (double) 350));
		articleService.saveArticle(new Article(null, "S7", "Samsung", 300.));
		articleService.saveArticle(new Article(null, "MI10", "Xiomi", 250D));
		articleService.saveArticle(new Article(null, "GalaxyTab", "Samsung", (double) 150));
		articleService.saveArticle(new Article(null, "EliteBook 16G", "HP", (double) 1350));
		articleService.saveArticle(new Article(null, "Ipad", "Apple", (double) 100));
		
		/*
		 * Some requests TEST
		 */
		System.out.println("\n-------------------------Read all articles------------------");
		articleService.realAll().forEach(a->{
			System.out.println(a);
		});
		//OR
//		for (Article article : articleRepository.findAll()) 
//			System.out.println(article);
		
		System.out.println("\n------------------------Read article by Id------------------");
		System.out.println(articleService.readById(2L));
		
		System.out.println("\n----------------------Delete article by Id------------------");
//		if(articleRepository.findById(2L).get() != null)
//			articleRepository.deleteById(2L);
		
		System.out.println("\n----------------------Update article ------------------------");
		Article art = new Article(null, "pc asus", "asus", (double) 1000);
		System.out.println(articleService.updateArticle(2L, art));
		
		System.out.println("\n------------------Read Article with price asc -------------------");
		articleService.readArticlesPriceASC().forEach(a->{
			System.out.println(a);
		});
		
		System.out.println("\n------------------Read Article with price desc -------------------");
		articleService.readArticlesPriceDESC().forEach(a->{
			System.out.println(a);
		});
	}

	
}
