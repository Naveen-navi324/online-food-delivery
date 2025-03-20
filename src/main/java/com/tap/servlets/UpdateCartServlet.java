package com.tap.servlets;

import com.tap.model.Cart;
import com.tap.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            // Get item ID and new quantity from request
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Validate quantity (must be at least 1)
            if (quantity < 1) {
                cart.removeCartItem(itemId);
            } else {
                // Update item quantity in the cart
                if (cart.getCartItems().containsKey(itemId)) {
                    CartItem item = cart.getCartItems().get(itemId);
                    item.setQuantity(quantity);
                    cart.getCartItems().put(itemId, item);
                }
            }
            
            // Save cart back to session
            session.setAttribute("cart", cart);
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Redirect back to cart page
        response.sendRedirect("cart.jsp");
    }
}
