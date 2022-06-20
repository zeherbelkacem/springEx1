package com.fms.springEx1.Controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Security.User;
import com.fms.springEx1.Security.UserService;
import com.fms.springEx1.Service.IArticleService;

import javassist.expr.NewArray;

@Controller
public class SecurityController {

	@Autowired
	private UserService userService;
	@Autowired
	private IArticleService articleService;

	/**
	 * 
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model) {
		if (userService.getUserId() == 0) {
			model.addAttribute("user", new User());
			return "loginHtml";
		}
		//model.addAttribute("user", new User());
		return "redirect:/shop/orderResume";
	}
	
	@GetMapping("shop/orderResume")
	public String orderResume(Model model) {
		
		long userId = userService.getUserId();
		User user = userService.readById(userId);
//		System.out.println(userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()));
		model.addAttribute("user", user);
		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("cartArticles", articlesCart.values());
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("date", new Date());
		return "orderResume";
	}
	

	@PostMapping("login/authenticate")
	public String authenticate(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "loginHtml";
		}
		if (userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
			model.addAttribute("loginError", "error");
			return "loginHtml";
		}
		return "redirect:/shop/orderResume";
	}
	
}
