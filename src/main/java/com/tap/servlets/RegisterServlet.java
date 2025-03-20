package com.tap.servlets;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final String INSERT_USER_QUERY = "INSERT INTO user (name, username, password, email, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL driver

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_db", "root", "Navi7892");
                 PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_QUERY)) {
                pstmt.setString(1, name);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.setString(4, email);
                pstmt.setString(5, address);
                pstmt.setString(6, phone);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect("login.jsp");  // Redirect to login after successful registration
                } else {
                    response.getWriter().println("Error registering user.");
                }
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
