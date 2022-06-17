package com.fms.springEx1.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Service.IArticleService;
import com.fms.springEx1.Service.ICategoryService;

@Controller
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	@RequestMapping("/shop")
	public String articleList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(name = "idToCart", defaultValue = "") Long idToCart) {

		if (idToCart != null) {
			articleService.addArticleToCart(idToCart);
			Map<Long, Article> articlesCart = articleService.getMyCart();
			model.addAttribute("totalPrice", articleService.getTotalSum());
			model.addAttribute("totalCartArticles", articlesCart.values().size() );
			//System.out.println(articlesCart.values());
			model.addAttribute("cartArticles", articlesCart.values());
		}

		/*
		 * Pagination without key word
		 */
//		Page<Article> articles = articleService.readArticlesPageByPage(page, size);
		/*
		 * Pagination using key word
		 */
		Page<Article> articles = articleService.findByPageByPageAndKeyWord(keyWord, PageRequest.of(page, size));

		List<Category> categories = categoryService.readAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("totalPages", articles.getTotalPages());
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("listArticle", articles);
		return "shop";
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("admin/saveArticleForm")
	public String saveArticleForm(Model model) {
		model.addAttribute("category", categoryService.readAllCategories());

		model.addAttribute("article", new Article());
		return "saveNewArticle";
	}

	/**
	 * 
	 * @param article
	 * @param bindingResult
	 * @param catName
	 * @return
	 */
	@PostMapping("admin/saveArticle")
	public String saveArticle(@Valid Article article, BindingResult bindingResult,
			@RequestParam("catName") String catName) {
		if (bindingResult.hasErrors()) {
			return "saveNewArticle";
		}
		System.out.println(article.getId());
		article.setCategory(categoryService.getCategoryByName(catName));
		articleService.saveArticle(article);
		return "redirect:/admin";
	}

	@GetMapping("admin/updateArticleForm")
	public String updateArticleForm(@RequestParam(name = "id", defaultValue = "") Long id, Model model) {
		model.addAttribute("articleCategoryName", articleService.readById(id).getCategory().getName());
		model.addAttribute("category", categoryService.readAllCategories());
		model.addAttribute("article", articleService.readById(id));
		return "saveNewArticle";
	}


}
