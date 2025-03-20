package com.tap.servlets;

import java.io.IOException;


import java.util.List;

import com.tap.daoimplementation.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    Integer restaurantId = null;

	    // Get restaurantId from request or session
	    String restaurantIdParam = req.getParameter("restaurantId");
	    if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
	        restaurantId = Integer.parseInt(restaurantIdParam);
	    } else {
	        restaurantId = (Integer) session.getAttribute("restaurantId");
	    }

	    // Debugging output
	    System.out.println("Restaurant ID received: " + restaurantId);

	    if (restaurantId != null) {
	        session.setAttribute("restaurantId", restaurantId);

	        MenuDAOImpl menuDAO = new MenuDAOImpl();
	        List<Menu> menuList = menuDAO.getMenuItemsByRestaurantId(restaurantId);

	        // Debugging output
	        System.out.println("Menu items fetched: " + (menuList != null ? menuList.size() : "null"));

	        req.setAttribute("menus", menuList);
	    } else {
	        System.out.println("No restaurantId found.");
	    }

	    RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
	    rd.forward(req, resp);
	}

}