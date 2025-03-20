package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cartItems;
	private int restaurantId;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem item) {
        int id = item.getId();
        if (cartItems.containsKey(id)) {
            cartItems.get(id).setQuantity(cartItems.get(id).getQuantity() + 1);
        } else {
            cartItems.put(id, item);
        }
    }

    public void updateCartItem(CartItem item, int quantity) {
        if (cartItems.containsKey(item.getId())) {
            cartItems.get(item.getId()).setQuantity(quantity);
        }
    }

    public void removeCartItem(int itemId) {
        cartItems.remove(itemId);
    }

    public double getTotalPrice() {
        return cartItems.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

	public void increaseQuantity(int itemId) {
		// TODO Auto-generated method stub
		
	}

	 public int getRestaurantId() {
	        return getRestaurantId();
	    }

	    public void setRestaurantId(int restaurantId) {
	        this.restaurantId = restaurantId;
	    }
		
	        public void clearCart() {
	            cartItems.clear();
	            this.restaurantId = -1; // Reset restaurant ID
	        
		
	}
}
