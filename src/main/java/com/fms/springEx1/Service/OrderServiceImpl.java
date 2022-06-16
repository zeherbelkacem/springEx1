package com.fms.springEx1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.springEx1.Entities.Order;
import com.fms.springEx1.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public long getLastOrderId() {
		long lastInsertedId = 0;
		List<Order> orders = orderRepository.findAll();
		if (orders.size() != 0) {
			Order lastOrder = orders.get(orders.size()-1);
			lastInsertedId = lastOrder.getId();
		}else lastInsertedId = 0;
		
		return lastInsertedId;
	}

	@Override
	public Order insertOrderLineToOrder(Order order) {
		return orderRepository.save(order);
	}

}
