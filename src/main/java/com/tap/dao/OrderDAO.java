package com.tap.dao;

import java.util.List;

import com.tap.model.Order;

public interface OrderDAO {
	void addOrder(Order oredr);
	Order getOrderById(int orderId);
	List<Order> getOrdersByUserId(int userId);
	List<Order> getOrderByRestaurantId(int restaurantId);
	List<Order> getAllOrders();
	void updateOrder(Order order);
	void deleteOrder(int orderId);

	

}