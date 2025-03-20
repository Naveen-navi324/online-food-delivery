package com.tap.dao;

import java.util.List;


import com.tap.model.Menu;
import com.tap.model.Restaurant;

public interface RestaurantDAO {
	
	 void addRestaurant(Restaurant restaurant);
	 Restaurant getRestaurantById(int restaurantId);
	 List<Restaurant> getAllRestaurants();
	 void updateRestaurant (Restaurant restaurant);
	 void deleteRestaurant(int restaurantId);
	 List<Restaurant> getActiveRestaurants();
	 
	 List<Restaurant> searchRestaurants(String query);
	
}