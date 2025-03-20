package com.tap.daoimplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_RESTAURANT_QUERY = "INSERT INTO restaurant (name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_RESTAURANT_QUERY = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_RESTAURANT_QUERY = "UPDATE restaurant SET name = ?, address = ?, phone = ?, rating = ?, cusineType = ?, isActive = ?, eta = ?, adminUserId = ?, imagePath = ? WHERE restaurantId = ?";
    private static final String DELETE_RESTAURANT_QUERY = "DELETE FROM restaurant WHERE restaurantId = ?";
    private static final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM restaurant";
    private static final String GET_ACTIVE_RESTAURANTS_QUERY = "SELECT * FROM restaurant WHERE isActive = 1";
    private static final String SEARCH_RESTAURANTS_QUERY = "SELECT * FROM restaurant WHERE name LIKE ? OR cusineType LIKE ?";

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            preparedStatement.setString(3, restaurant.getPhone());
            preparedStatement.setDouble(4, restaurant.getRating());
            preparedStatement.setString(5, restaurant.getCusineType());
            preparedStatement.setBoolean(6, restaurant.isActive());
            preparedStatement.setInt(7, restaurant.getEta());
            preparedStatement.setInt(8, restaurant.getAdminUserId());
            preparedStatement.setString(9, restaurant.getImagePath());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       
    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                restaurant = extractRestaurant(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            preparedStatement.setString(3, restaurant.getPhone());
            preparedStatement.setDouble(4, restaurant.getRating());
            preparedStatement.setString(5, restaurant.getCusineType());
            preparedStatement.setBoolean(6, restaurant.isActive());
            preparedStatement.setInt(7, restaurant.getEta());
            preparedStatement.setInt(8, restaurant.getAdminUserId());
            preparedStatement.setString(9, restaurant.getImagePath());
            preparedStatement.setInt(10, restaurant.getRestaurantId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  

    @Override
    public List<Restaurant> getAllRestaurants() {          
        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_RESTAURANTS_QUERY)) {

            while (resultSet.next()) {
                Restaurant restaurant = extractRestaurant(resultSet);
                restaurantList.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public List<Restaurant> getActiveRestaurants() {
        List<Restaurant> activeRestaurants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_RESTAURANTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Restaurant restaurant = extractRestaurant(resultSet);
                activeRestaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeRestaurants;
    }
    
    
    public List<Restaurant> searchRestaurants(String query) {
        List<Restaurant> restaurantList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SEARCH_RESTAURANTS_QUERY)) {

            ps.setString(1, "%" + query + "%"); // Search by restaurant name
            ps.setString(2, "%" + query + "%"); // Search by cuisine type
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant restaurant = extractRestaurant(rs);
                restaurantList.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }
    
    
    
    private Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {
        int restaurantId = resultSet.getInt("restaurantId");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        Double rating = resultSet.getDouble("rating");
        String cusineType = resultSet.getString("cusineType");
        boolean isActive = resultSet.getBoolean("isActive");
        int eta = resultSet.getInt("eta");
        int adminUserId = resultSet.getInt("adminUserId");
        String imagePath = resultSet.getString("imagePath");

        return new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
    }
}
