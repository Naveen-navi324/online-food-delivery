package com.tap.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.utility.DBConnection;

@WebServlet("/process-checkout")
public class ProcessCheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get user input
        String fullAddress = request.getParameter("fullAddress");
        String paymentMode = request.getParameter("paymentMode");

        if (fullAddress == null || fullAddress.isEmpty() || paymentMode == null || paymentMode.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in all fields.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        // Retrieve cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCartItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        Connection conn = null;
        PreparedStatement orderStmt = null, itemStmt = null;
        ResultSet rs = null;
        int orderId = 0;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Get restaurantId (Replace with dynamic retrieval)
            int restaurantId = 1; // Example: replace with actual restaurantId from session or request

            // Step 1: Insert Order into `orders` table (Fixed: Added restaurantId)
            String insertOrderSQL = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
            orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, 1); // Replace with actual userId from session if available
            orderStmt.setInt(2, restaurantId);
            orderStmt.setTimestamp(3, new Timestamp(new Date().getTime())); // Current timestamp
            orderStmt.setDouble(4, cart.getTotalPrice());
            orderStmt.setString(5, "Pending");
            orderStmt.setString(6, paymentMode);

            int rowsAffected = orderStmt.executeUpdate();
            if (rowsAffected > 0) {
                rs = orderStmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1); // Retrieve generated order ID
                }
            }

            if (orderId == 0) {
                throw new Exception("Failed to insert order.");
            }

            // Step 2: Insert Order Items into `order_items` table
            String insertItemSQL = "INSERT INTO order_items (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
            itemStmt = conn.prepareStatement(insertItemSQL);

            for (CartItem item : cart.getCartItems().values()) {
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getQuantity() * item.getPrice());
                itemStmt.addBatch();
            }

            itemStmt.executeBatch(); // Execute batch insert for all items
            conn.commit(); // Commit transaction

            // Clear cart after successful order placement
            session.removeAttribute("cart");

            // Redirect to success page
            response.sendRedirect("orderSuccess.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); // Rollback on failure
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            response.sendRedirect("checkout.jsp?error=Order processing failed.");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (orderStmt != null) orderStmt.close(); } catch (Exception e) {}
            try { if (itemStmt != null) itemStmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
