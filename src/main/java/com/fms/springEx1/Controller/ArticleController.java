package com.fms.springEx1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Null;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Exceptions.NotFoundEntityException;
import com.fms.springEx1.Service.IArticleService;
import com.fms.springEx1.Service.ICategoryService;

@Controller
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	@PostMapping("addToCart")
	public String addArticleToCart(Model model, RedirectAttributes attributes,
			@RequestParam(name = "idToCart", defaultValue = "") Long idToCart,
			@RequestParam(name = "quantity", defaultValue = "1") int quantity, @RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size, @RequestParam(name = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(name = "catName", defaultValue = "") String catName) {
		if (!catName.equals("")) {
			articleService.addArticleToCart(idToCart, quantity);
			attributes.addAttribute("page", page);
			attributes.addAttribute("size", size);
			attributes.addAttribute("keyWord", keyWord);
			attributes.addAttribute("catName", catName);
			return "redirect:articlesByCategory";
		}
		articleService.addArticleToCart(idToCart, quantity);
		attributes.addAttribute("page", page);
		attributes.addAttribute("size", size);
		attributes.addAttribute("keyWord", keyWord);
		return "redirect:/";
	}

	@RequestMapping("/")
	public String articleList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(name = "catName", defaultValue = "") String catName,
			@RequestParam(name = "idToCart", defaultValue = "") Long idToCart,
			@RequestParam(name = "idToRm", defaultValue = "") Long idToRm,
			@RequestParam(name = "quantity", defaultValue = "1") int quantity) {

		String listOf = "List of articles";

		if (idToRm != null)
			articleService.removeArticleFromCart(idToRm);
		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("totalCartArticles", articlesCart.values().size());
		model.addAttribute("cartArticles", articlesCart.values());

		/*
		 * Pagination using key word
		 */

		Page<Article> articles;
//		if (!catName.equalsIgnoreCase("")) {
//			System.out.println("je suisla");
//			articles = articleService.readByBrandContainsAndCategoryName(keyWord, catName, PageRequest.of(page, size));
//			listOf = "Articles of " + catName + " caterory";
////			Page<Article> articles = articleService.findByPageByPageAndCategoryName(catName, PageRequest.of(page, size));
////			if (articles.isEmpty())
////				articles.isEmpty(); // initialize articles page to empty
//		}
//		else
		articles = articleService.findByPageByPageAndKeyWord(keyWord, PageRequest.of(page, size));

		List<Category> categories = categoryService.readAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("totalPages", articles.getTotalPages());
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("listArticle", articles);
		model.addAttribute("listOf", listOf);
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
			@RequestParam("catName") String catName, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println(article.getId());
			if (article.getId() != null) {
				redirectAttributes.addAttribute("id", article.getId());
				return "redirect:/admin/updateArticleForm";
			} else
				return "redirect:/admin/saveArticleForm";
		}
		article.setCategory(categoryService.getCategoryByName(catName));
		articleService.saveArticle(article);
		return "redirect:/admin";
	}

	@GetMapping("admin/updateArticleForm")
	public String updateArticleForm(@RequestParam(name = "id", defaultValue = "") Long id, Model model) {
		System.out.println("upd " + id);
		List<String> CategoryNames = new ArrayList<String>();
		for (Category c : categoryService.readAllCategories()) {
			CategoryNames.add(c.getName());
		}
		model.addAttribute("categoriesName", CategoryNames);
		model.addAttribute("category", categoryService.readAllCategories());
		model.addAttribute("article", articleService.readById(id));
		return "saveNewArticle";
	}

	/**
	 * 
	 * @param name
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("articlesByCategory")
	public String articlesByCategory(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "catName", defaultValue = "") String catName,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(name = "idToRm", defaultValue = "") Long idToRm,
			@RequestParam(name = "idToCart", defaultValue = "") Long idToCart) {

		if (idToRm != null)
			articleService.removeArticleFromCart(idToRm);
		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("totalCartArticles", articlesCart.values().size());
		model.addAttribute("cartArticles", articlesCart.values());

		Page<Article> articles = articleService.readByDescriptionContainsAndCategoryName(keyWord, catName,
				PageRequest.of(page, size));
		if (articles.isEmpty())
			articles.isEmpty(); // initialize articles page to empty

		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("totalPages", articles.getTotalPages());
		model.addAttribute("listOf", "Articles of " + catName + " caterory");
		model.addAttribute("listArticle", articles);
		model.addAttribute("categories", categoryService.readAllCategories());
		model.addAttribute("catName", catName);

		return "articlesByCategory";
	}

	@RequestMapping("cart")
	public String articleList(Model model, @RequestParam(name = "idToRm", defaultValue = "") Long idToRm) {
		String cartEmpty = null;
		if (articleService.getMyCart().isEmpty())
			throw new NotFoundEntityException("Your cart is empty");
		if (idToRm != null) {
			articleService.removeArticleFromCart(idToRm);
			if (articleService.getMyCart().isEmpty())
				cartEmpty = "empty";
		}

		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("totalCartArticles", articlesCart.values().size());
		model.addAttribute("cartArticles", articlesCart.values());
		model.addAttribute("cartEmpty", cartEmpty);
		return "cart";
	}

}
