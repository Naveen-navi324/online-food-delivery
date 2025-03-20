package com.tap.daoimplementation;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    // Method to add a new menu item
    @Override
    public void addMenuItem(Menu menu) {
        String query = "INSERT INTO Menu (menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, menu.getMenuId());
            statement.setInt(2, menu.getRestaurantId());
            statement.setString(3, menu.getItemName());
            statement.setString(4, menu.getDescription());
            statement.setDouble(5, menu.getPrice());
            statement.setDouble(6, menu.getRatings());
            statement.setBoolean(7, menu.isAvailable());
            statement.setString(8, menu.getImagePath());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
        }
    }

    // Method to retrieve a menu item by its ID
    @Override
    public Menu getMenuItemById(int menuId) {
       String GET_MENU_ITEM_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
        Menu menu = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_MENU_ITEM_BY_ID)) {
            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	 menu = extractMenu(rs);  // Use the extractMenu method to ensure all fields are correctly set
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (menu == null) {
            System.out.println("DEBUG: Menu item with ID " + menuId + " not found!");
        } else {
            System.out.println("DEBUG: Retrieved menu item: " + menu.getItemName());
        }
        return menu;
    }

    // Method to retrieve all menu items for a specific restaurant
    @Override
    public List<Menu> getMenuItemsByRestaurantId(int restaurantId) {
        String query = "SELECT * FROM Menu WHERE restaurantId = ?";
        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                menuList.add(extractMenu(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu items by restaurant: " + e.getMessage());
        }
        return menuList;
    }

    // Method to retrieve all menu items
    @Override
    public List<Menu> getAllMenuItems() {
        String query = "SELECT * FROM Menu";
        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                menuList.add(extractMenu(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all menu items: " + e.getMessage());
        }
        return menuList;
    }

    // Method to update an existing menu item
    @Override
    public void updateMenuItem(Menu menu) {
        String query = "UPDATE Menu SET restaurantId = ?, itemName = ?, description = ?, price = ?, ratings = ?, isAvailable = ?, imagePath = ? WHERE menuId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, menu.getRestaurantId());
            statement.setString(2, menu.getItemName());
            statement.setString(3, menu.getDescription());
            statement.setDouble(4, menu.getPrice());
            statement.setDouble(5, menu.getRatings());
            statement.setBoolean(6, menu.isAvailable());
            statement.setString(7, menu.getImagePath());
            statement.setInt(8, menu.getMenuId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating menu item: " + e.getMessage());
        }
    }

    // Method to delete a menu item by its ID
    @Override
    public void deleteMenuItem(int menuId) {
        String query = "DELETE FROM Menu WHERE menuId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, menuId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting menu item: " + e.getMessage());
        }
    }

    // Method to search menu items by name (partial match)
    @Override
    public List<Menu> searchMenuItemsByName(String itemName) {
        String query = "SELECT * FROM Menu WHERE itemName LIKE ?";
        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + itemName + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                menuList.add(extractMenu(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error searching menu items: " + e.getMessage());
        }
        return menuList;
    }

    // Method to retrieve available menu items
    @Override
    public List<Menu> getAvailableMenuItems() {
        String query = "SELECT * FROM Menu WHERE isAvailable = true";
        List<Menu> menuList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                menuList.add(extractMenu(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving available menu items: " + e.getMessage());
        }
        return menuList;
    }

    // Helper method to extract a Menu object from a ResultSet
    private static Menu extractMenu(ResultSet resultSet) throws SQLException {
        return new Menu(
            resultSet.getInt("menuId"),
            resultSet.getInt("restaurantId"),
            resultSet.getString("itemName"),
            resultSet.getString("description"),
            resultSet.getDouble("price"),
            resultSet.getDouble("ratings"),
            resultSet.getBoolean("isAvailable"),
            resultSet.getString("imagePath")
        );
    }

	public Menu getMenu(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}