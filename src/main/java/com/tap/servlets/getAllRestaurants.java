package com.tap.servlets;

import java.io.IOException;

import java.util.List;
import com.tap.dao.RestaurantDAO;
import com.tap.daoimplementation.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getAllRestaurants") // URL Mapping
public class getAllRestaurants extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get Restaurant DAO instance
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        
        // Fetch all restaurants from the database
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        
        // Store the restaurants in request scope
        request.setAttribute("restaurants", restaurants);
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
