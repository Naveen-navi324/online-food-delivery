package com.tap.daoimplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    // SQL Queries
    private static final String INSERT_ORDER_ITEM_QUERY = "INSERT INTO order_items (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
    private static final String GET_ORDER_ITEM_BY_ID_QUERY = "SELECT * FROM order_items WHERE orderItemId = ?";
    private static final String GET_ORDER_ITEMS_BY_ORDER_ID_QUERY =  "SELECT * FROM order_items WHERE orderId = ?";
    private static final String GET_ORDER_ITEMS_BY_MENU_ID_QUERY =  "SELECT * FROM order_items WHERE menuId = ?"; // New query
    private static final String GET_ALL_ORDER_ITEMS_QUERY =  "SELECT * FROM order_items";
    private static final String UPDATE_ORDER_ITEM_QUERY = "UPDATE order_items SET orderId = ?, menuId = ?, quantity = ?, totalPrice = ? WHERE orderItemId = ?";
    private static final String DELETE_ORDER_ITEM_QUERY =  "DELETE FROM order_items WHERE orderItemId = ?";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getTotalprice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        OrderItem orderItem = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEM_BY_ID_QUERY)) {

            preparedStatement.setInt(1, orderItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderItem = extractOrderItem(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID_QUERY)) {

            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItem(resultSet);
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getOrderItemsByMenuId(int menuId) {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BY_MENU_ID_QUERY)) {

            preparedStatement.setInt(1, menuId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItem(resultSet);
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_ORDER_ITEMS_QUERY)) {

            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItem(resultSet);
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getTotalprice());
            preparedStatement.setInt(5, orderItem.getOrderItemId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        int orderItemId = resultSet.getInt("orderItemId");
        int orderId = resultSet.getInt("orderId");
        int menuId = resultSet.getInt("menuId");
        int quantity = resultSet.getInt("quantity");
        double totalPrice = resultSet.getDouble("totalPrice");

        return new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
    }
}
