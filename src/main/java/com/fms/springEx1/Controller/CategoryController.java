package com.fms.springEx1.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Service.ICategoryService;

@Controller
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("admin/categories/saveCategoryForm")
	public String saveArticleForm(Model model) {
		model.addAttribute("category", new Category());
		return "saveNewCategory";
	}
	
	/**
	 * 
	 * @param article
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("admin/categories/saveCategory")
	public String saveArticle(@Valid Category category, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "saveNewCategory";
		}
		
		categoryService.saveCategory(category);
		return "redirect:/admin/categories";
	}
}
