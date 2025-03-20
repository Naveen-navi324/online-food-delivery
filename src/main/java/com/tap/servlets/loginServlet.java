package com.tap.servlets;
import java.io.IOException;

import com.tap.dao.UserDAO;
import com.tap.daoimplementation.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class loginServlet extends HomeServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAOImpl();
        String userName = dao.validateUser(email, password);

        if (userName != null) {
            // Create session and store user details
            HttpSession session = request.getSession();
            session.setAttribute("user", userName);
            session.setAttribute("email", email); // Store email if needed

            // Redirect to home.jsp
            response.sendRedirect("home.jsp");
        } else {
            // If login fails, show an error message
            request.setAttribute("errorMessage", "Invalid Email or Password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
