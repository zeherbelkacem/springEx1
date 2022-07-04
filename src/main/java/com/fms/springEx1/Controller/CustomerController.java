package com.fms.springEx1.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fms.springEx1.Security.UserServiceImpl;
import com.fms.springEx1.Security.Uuser;






@Controller
public class CustomerController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping ("customerProfile")
	public String profile (Model model, Principal principal) {
		Uuser user = userService.findUuserByUserName(principal.getName());
		model.addAttribute("userCustomersList", user.getCustomers());
		return "customerProfile";
	}
	
//	@GetMapping ("/profileOrder")
//	public String profileOrder (Model model, Principal principal,@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "size", defaultValue = "4") int size) {
//		//Uuser user = userService.findUuserByUserName(principal.getName());
//		Page<Order> ordersPages = orderService.ordersPageByPage(PageRequest.of(page, size));
//		model.addAttribute("ordersList", ordersPages);
//		return "profileOrder";
//		
//	}
	
	@GetMapping ("userProfile")
	public String customerProfile (Model model, Principal principal) {
		Uuser user = userService.findUuserByUserName(principal.getName());
		model.addAttribute("uuser", user);
		return "seeMyProfile";
		
	}
	

	
}