package com.fms.springEx1.Controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
import com.fms.springEx1.Security.UserService;
import com.fms.springEx1.Security.Uuser;
import com.fms.springEx1.Service.IArticleService;

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
//	@GetMapping("/login")
//	public String login(Model model) {
//		if (userService.getUserId() == 0) {
//			model.addAttribute("user", new Uuser());
//			return "loginHtml";
//		}
//		//model.addAttribute("user", new User());
//		return "redirect:/shop/orderResume";
//	}
	
	@RequestMapping("/logout")
	public String logoutForm(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:login";
	}
	
	
	@GetMapping("shop/orderResume")
	public String orderResume(Model model) {
		
		long userId = userService.getUserId();
		Uuser user = userService.readById(userId);
//		System.out.println(userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()));
		model.addAttribute("user", user);
		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("cartArticles", articlesCart.values());
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("date", new Date());
		return "orderResume";
	}
	

	@PostMapping("login/authenticate")
	public String authenticate(@Valid Uuser user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "loginHtml";
		}
		if (userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
			model.addAttribute("loginError", "error");
			return "loginHtml";
		}
		return "redirect:/shop/orderResume";
	}
	
	@RequestMapping("admin/users")
	public String users(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "userId", defaultValue = "") Long userId) {

	
		if (userId != null)
			articleService.deleteArticleById(userId);

		Page<Uuser> users = userService.findByPageByPageAndEmail(email, PageRequest.of(page, size));
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[users.getTotalPages()]);
		model.addAttribute("totalPages", users.getTotalPages());
		model.addAttribute("email", email);
		model.addAttribute("listUsers", users.getContent());
		model.addAttribute("listOf", "List of users");
		return "users";
	}
	
	@RequestMapping("admin/saveUserForm")
	public String saveUser(Model model) {
		model.addAttribute("user", new Uuser());
		return "saveNewUser";
	}
	
}
