package com.tap.servlets;

import com.tap.model.Cart;
import com.tap.model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.addCartItem(new CartItem(itemId, name, price, quantity));
        } else if ("update".equals(action)) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            updateCartItem(cart, itemId, quantity);
        } else if ("remove".equals(action)) {
            removeCartItem(cart, itemId);
        }

        session.setAttribute("cart", cart);

        // ✅ Redirect back to the referring page (menu.jsp or cart.jsp)
        String referer = request.getHeader("referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/menu"); // Default fallback
        }
    }

    private void updateCartItem(Cart cart, int itemId, int quantity) {
        if (cart.getCartItems().containsKey(itemId)) {
            if (quantity > 0) {
                cart.updateCartItem(cart.getCartItems().get(itemId), quantity);
            } else {
                cart.removeCartItem(itemId);
            }
        }
    }

    private void removeCartItem(Cart cart, int itemId) {
        cart.removeCartItem(itemId);
    }
}
