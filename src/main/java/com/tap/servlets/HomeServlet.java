package com.tap.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import com.tap.daoimplementation.RestaurantDAOImpl;
import com.tap.model.Restaurant;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("homeServlet is called");
      try {
    	  RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
          
          List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
          
          req.setAttribute("restaurants", allRestaurants);
       
          RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
          rd.forward(req, resp);
		
	} catch (Exception e) {
		e.printStackTrace();
	} 
      
  
   }
    }
