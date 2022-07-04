package com.fms.springEx1.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Customer;
import com.fms.springEx1.Security.UserService;
import com.fms.springEx1.Security.Uuser;
import com.fms.springEx1.Service.ArticleService;
import com.fms.springEx1.Service.CustomerService;

@Controller
public class CartController {

	private CustomerService customerService;
	private ArticleService articleService;
	private UserService userService;

	public CartController(CustomerService customerService, ArticleService articleService, UserService userService) {
		this.customerService = customerService;
		this.articleService = articleService;
		this.userService = userService;
	}

	@GetMapping("updateCustomerForm")
	public String updateCustomerForm(Model model, @RequestParam(name = "customerId") Long customerId) {
		model.addAttribute("customer", customerService.readById(customerId));
		return "updateCustomer";
	}

	@PostMapping("updateCustomer")
	public String updateCustomer(@Valid Customer customer, BindingResult bindingResult, Principal principal,
			Model model, RedirectAttributes attributes, @RequestParam(name = "id", defaultValue = "") Long id) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("customer", customerService.readById(id));
			return "redirect:updateCustomer";
		}
		Uuser user = userService.findUuserByUserName(principal.getName());
		customer.setUuser(user);
		customerService.saveCustomer(customer);
		return "redirect:myCustomers";
	}



	@GetMapping("myCustomers")
	public String chooseCustomer(Model model, Principal principal,
			@RequestParam(name = "IdToRm", defaultValue = "") Long IdToRm) {
		if (IdToRm != null) {
			customerService.deleteCustomerById(IdToRm);
			return "redirect:myCustomers";
		}

		Uuser user = userService.findUuserByUserName(principal.getName());

		if (user.getCustomers().size() == 0) {
			model.addAttribute("customer", new Customer());
			return "saveNewCustomer";
		}

		model.addAttribute("userCustomersList", user.getCustomers());
		return "userCustomersList";
	}

	@GetMapping("saveNewCustomerForm")
	public String saveCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "saveNewCustomer";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@Valid Customer customer, BindingResult bindingResult, Principal principal, Model model,
			RedirectAttributes attributes, @RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "address2", defaultValue = "") String address2,
			@RequestParam(name = "zip", defaultValue = "") String zip,
			@RequestParam(name = "city", defaultValue = "") String city,
			@RequestParam(name = "state", defaultValue = "") String state) {
		if (bindingResult.hasErrors()) {
			return "saveNewCustomer";
		}
		Uuser user = userService.findUuserByUserName(principal.getName());
		customer.setAddress(address + " /" + address2 + ", " + zip + " " + city + ", " + state);
		customer.setUuser(user);
		customerService.saveCustomer(customer);
		attributes.addAttribute("customerId", customerService.readByPhone(customer.getPhone()).getId());
		return "redirect:orderResume";
	}

	@GetMapping("orderResume")
	public String orderResume(Model model, Principal principal,
			@RequestParam(name = "customerId", defaultValue = "") Long customerId) {
		// Uuser user = userService.findUuserByUserName(principal.getName());
		model.addAttribute("customer", customerService.readById(customerId));
		Map<Long, Article> articlesCart = articleService.getMyCart();
		model.addAttribute("cartArticles", articlesCart.values());
		model.addAttribute("totalPrice", articleService.getTotalSum());
		model.addAttribute("date", new Date());
		return "orderResume";
	}
}
