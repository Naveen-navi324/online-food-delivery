package com.tap.dao;

import com.tap.model.Menu;
import java.util.List;

public interface MenuDAO {
    // Create a new menu item
    void addMenuItem(Menu menu);

    // Retrieve a menu item by its ID
    Menu getMenuItemById(int menuId);

    // Retrieve all menu items for a specific restaurant
    List<Menu> getMenuItemsByRestaurantId(int restaurantId);

    // Retrieve all menu items
    List<Menu> getAllMenuItems();

    // Update an existing menu item
    void updateMenuItem(Menu menu);

    // Delete a menu item by its ID
    void deleteMenuItem(int menuId);

    // Search menu items by name (partial match)
    List<Menu> searchMenuItemsByName(String itemName);

    // Retrieve available menu items (isAvailable = true)
    List<Menu> getAvailableMenuItems();
}