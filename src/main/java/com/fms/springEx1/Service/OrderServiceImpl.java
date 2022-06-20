package com.fms.springEx1.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Order;
import com.fms.springEx1.Entities.OrderItem;
import com.fms.springEx1.Repository.OrderRepository;
import com.fms.springEx1.Security.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private OrderItemService orderItemService;

	@Override
	public long getLastOrderId() {
		long lastInsertedId = 0;
		List<Order> orders = orderRepository.findAll();
		if (orders.size() != 0) {
			Order lastOrder = orders.get(orders.size() - 1);
			lastInsertedId = lastOrder.getId();
		} else
			lastInsertedId = 0;

		return lastInsertedId;
	}

	@Override
	public Order insertOrderLineToOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order saveOrder() {
		/*
		 * As we can't add or update a child (orderitem) row (foreign key constraint,
		 * start by save a default parent row (order)
		 */
		orderRepository.save(new Order(0, new Date(), articleService.getTotalSum(), null,
				userService.readById(userService.getUserId())));
		long lastOrderId = getLastOrderId();

		/* each line in the bucket corresponds to an item in the order table */
		for (Article article : articleService.getMyCart().values())
			orderItemService.saveOrderItem(new OrderItem(0, article.getQuantity(),
					article.getQuantity() * article.getPrice(), orderRepository.findById(lastOrderId).get(), article));

		/*
		 * Update the last default order saving after that all order items were saved
		 * (and clear the bucket)
		 */
		Order orderToSave = new Order(lastOrderId, new Date(), articleService.getTotalSum(), null,
				userService.readById(userService.getUserId()));
		articleService.getMyCart().clear();

		return orderRepository.save(orderToSave);
	}

	@Override
	public Page<Order> ordersPageByPage(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

}
