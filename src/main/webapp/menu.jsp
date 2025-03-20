<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, com.tap.model.Menu, com.tap.model.Cart, com.tap.model.CartItem" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="menuStyle.css">
</head>
<body> 

<div class="min-h-screen bg-gray-50">
    <!-- Header Section -->
    <header class="header">
        <div class="header-container">
            <div class="logo">Food Series</div>
            <nav class="nav">
            <a href="home.jsp"><i class="fa fa-home"></i> Home</a>

                <button class="nav-button" onclick="window.location.href='cart.jsp'">
                    🛒 Cart (<span>
                        <% 
                            Cart cart = (Cart) session.getAttribute("cart");
                            out.print(cart != null ? cart.getCartItems().size() : 0);
                        %>
                    </span>)
                </button>
                <button class="nav-button" onclick="window.location.href='logout.jsp'">
                     Logout
                </button>
            </nav>
        </div>
    </header>

    <h2 class="menu-heading">Menu List of Restaurant</h2>

    <!-- Menu Items -->
    <div class="menu-grid">
        <% 
            List<Menu> menuList = (List<Menu>) request.getAttribute("menus");
            if (menuList != null) { 
                // Get cart items map for quick lookup
                Map<Integer, CartItem> cartItems = (cart != null) ? cart.getCartItems() : null;

                for (Menu m : menuList) { 
                    // Check if the item is in the cart
                    CartItem cartItem = (cartItems != null) ? cartItems.get(m.getMenuId()) : null;
                    int quantity = (cartItem != null) ? cartItem.getQuantity() : 0;
        %>
        <div class="menu-item">
            <img src="<%= m.getImagePath() %>" alt="<%= m.getItemName() %>">
            <h3><%= m.getItemName() %></h3>
            <p class="description"><%= m.getDescription() %></p>
            <p class="price">Price: ₹<%= m.getPrice() %></p>
            <p class="availability">Is Available: Yes</p>

            <% if (quantity > 0) { %>
                <!-- If item is in cart, show quantity & update buttons -->
                <form action="cart" method="post">
    <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
    <input type="hidden" name="action" value="update">

    <!-- Decrease Quantity -->
    <button type="submit" name="quantity" value="<%= quantity - 1 %>" <%= (quantity <= 1) ? "disabled" : "" %>>-</button>

    <!-- Show Quantity -->
    <span class="quantity-value"><%= quantity %></span>

    <!-- Increase Quantity -->
    <button type="submit" name="quantity" value="<%= quantity + 1 %>">+</button>
</form>

                
            <% } else { %>
                <!-- Add to Cart Form -->
                <form action="cart" method="post">
                    <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
                    <input type="hidden" name="name" value="<%= m.getItemName() %>">
                    <input type="hidden" name="price" value="<%= m.getPrice() %>">
                    <input type="hidden" name="action" value="add">Qty
                    <input type="number" name="quantity" value="1" min="1">
                    <button type="submit">Add to Cart</button>
                </form>
            <% } %>
        </div>
        <% } } else { %>
            <p>No menu items available.</p>
        <% } %>
    </div>
</div>
  <footer class="footer">
        <div class="footer-column">
            <h3>Company</h3>
            <ul>
                <li><a href="#">About us</a></li>
                <li><a href="#">Team</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">Blog</a></li>
            </ul>
        </div>
        <div class="footer-column">
            <h3>Contact</h3>
            <ul>
                <li><a href="#">Help & Support</a></li>
                <li><a href="#">Partner with us</a></li>
                <li><a href="#">Ride with us</a></li>
            </ul>
        </div>
        <div class="footer-column">
            <h3>Legal</h3>
            <ul>
                <li><a href="#">Terms & Conditions</a></li>
                <li><a href="#">Refund & Cancellation</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Cookie Policy</a></li>
            </ul>
        </div>
        <div class="footer-column">
            <h3>Social Links</h3>
            <ul>
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">YouTube</a></li>
            </ul>
        </div>
    </footer>

</body>
</html>
