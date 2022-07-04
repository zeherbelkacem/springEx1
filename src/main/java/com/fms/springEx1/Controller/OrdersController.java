package com.fms.springEx1.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fms.springEx1.Entities.Order;
import com.fms.springEx1.Security.UserService;
import com.fms.springEx1.Security.Uuser;
import com.fms.springEx1.Service.OrderService;

@Controller
public class OrdersController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@RequestMapping("saveOrder")
	public String saveOrder(@RequestParam(name = "customerId", defaultValue = "") Long customerId,
			RedirectAttributes model) {
		model.addAttribute("customerId", customerId);
		orderService.saveOrder(customerId);

		return "redirect:/listOrders";
	}

	/**
	 * Méthode qui renvoit la liste des commandes pour un utilisateur
	 * 
	 * @param model
	 * @param principal
	 * @param customerId
	 * @return
	 */
	@GetMapping("listOrders")
	public String listOrders(Model model, Principal principal,
			@RequestParam(name = "customerId", defaultValue = "") Long customerId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "4") int size) {
		
		Uuser user = userService.findUuserByUserName(principal.getName());
		List<Order> ordersList = new ArrayList<>();
		user.getCustomers().forEach(c -> {
			ordersList.addAll(c.getOrders());
		});
		/*
		 * Convert java List to spring Page
		 */
		//Page<Order> orders = new PageImpl<>(ordersList);
		Pageable pageable = PageRequest.of(page, size);
		final int start = (int)pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), ordersList.size());
		final Page<Order> orders = new PageImpl<>(ordersList.subList(start, end), pageable, ordersList.size());
		//model
		model.addAttribute("orders", orders);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[orders.getTotalPages()]);
		model.addAttribute("totalPages", orders.getTotalPages());

		return "customerOrders";
	}

	@RequestMapping("admin/orders")
	public String adminOrders(Model model, @RequestParam(name = "orderId", defaultValue = "") Long orderId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "4") int size

	) {
		if (orderId != null)
			orderService.loadInvoice(orderId);
		Page<Order> ordersPages = orderService.ordersPageByPage(PageRequest.of(page, size));
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[ordersPages.getTotalPages()]);
		model.addAttribute("totalPages", ordersPages.getTotalPages());
		model.addAttribute("listOf", "List of Orders");
		model.addAttribute("listOrders", ordersPages);
		return "orders";
	}

	@RequestMapping("orderDetails")
	public String orderDetails(@RequestParam(name = "orderId", defaultValue = "") Long orderId, Model model) {
		model.addAttribute("order", orderService.getOrderById(orderId));
		return "orderDetails";
	}
}
