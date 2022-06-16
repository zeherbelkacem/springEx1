package com.fms.springEx1.Service;

import com.fms.springEx1.Entities.Order;

public interface OrderService {

	public long getLastOrderId();

	public Order insertOrderLineToOrder(Order order);
}
