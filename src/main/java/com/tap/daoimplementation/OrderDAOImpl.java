package com.tap.daoimplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID_QUERY = "SELECT * FROM orders WHERE orderId = ?";
    private static final String GET_ORDERS_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE userId = ?";
    private static final String GET_ORDERS_BY_RESTAURANT_ID_QUERY = "SELECT * FROM orders WHERE restaurantId = ?";
    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String UPDATE_ORDER_QUERY ="UPDATE orders SET userId = ?, restaurantId = ?, orderDate = ?, totalAmount = ?, status = ?, paymentMode = ? WHERE orderId = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE orderId = ?";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setDouble(4, order.getTotalAmount());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getPaymentMode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID_QUERY)) {

            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = extractOrder(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER_ID_QUERY)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getOrderByRestaurantId(int restaurantId) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_RESTAURANT_ID_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS_QUERY)) {

            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void updateOrder(Order order) {
             try (Connection connection = DBConnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setDouble(4, order.getTotalAmount());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getPaymentMode());
            preparedStatement.setInt(7, order.getOrderId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order extractOrder(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("orderId");
        int userId = resultSet.getInt("userId");
        int restaurantId = resultSet.getInt("restaurantId");
        Date orderDate = new Date(resultSet.getTimestamp("orderDate").getTime());
        double totalAmount = resultSet.getDouble("totalAmount");
        String status = resultSet.getString("status");
        String paymentMode = resultSet.getString("paymentMode");

        return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
    }
}
