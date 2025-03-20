package com.tap.dao;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDAO {
	
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItemById(int orderItemId);
	List<OrderItem> getOrderItemsByOrderId(int orderId);
	List<OrderItem> getOrderItemsByMenuId(int menuId);
	List<OrderItem> getAllOrderItems();
    void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	
	
	

}