<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, com.tap.model.Cart, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - Food Series</title>
    <link rel="stylesheet" href="cart.css">
    
</head>
<body>
<div class="min-h-screen bg-gray-50">
    <header class="header">
        <div class="header-container">
            <div class="logo">Food Series</div>
            <nav class="nav"> 
                <a href="home.jsp">Home</a>
                <a href="logout.jsp">Logout</a>
            </nav>
        </div>
    </header>
</div>

<div class="container">
    <h2>Your Cart</h2>
    <%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
%>

    <div class="cart-items">
        <%
           
            boolean isCartEmpty = (cart == null || cart.getCartItems().isEmpty());

            if (!isCartEmpty) {
                for (Map.Entry<Integer, CartItem> entry : cart.getCartItems().entrySet()) {
                    CartItem item = entry.getValue();
        %>
        <div class="cart-item">
            <div class="cart-item-details">
                <h3><%= item.getName() %></h3>
                <p>Price: ₹<%= item.getPrice() %></p>
               <p>Quantity:<%= item.getQuantity() %></p>
                <p>Total: ₹<%= item.getPrice() %></p>
            </div>
            
            
                    <!-- Remove Item -->
                   <form action="cart" method="post" class="remove-form">
    <input type="hidden" name="itemId" value="<%= item.getId() %>">
    <input type="hidden" name="action" value="remove">
    <button type="submit" class="remove-btn">Remove</button>
</form>


                </div>
            </div>
        <%
                }
            } else {
        %>
        <div class="empty-cart-message">
            <p>Your cart is empty.</p>
        </div>
        <% } %>
    </div>

    <div class="cart-summary">
        <% if (!isCartEmpty) { %>
            <h3>Total amount: ₹<%= cart.getTotalPrice() %></h3>
        <% } %>

        <button onclick="window.location.href='menu'" class="cart-btn">Add More Items</button>
        
        <% if (!isCartEmpty) { %>
            <button onclick="window.location.href='checkout.jsp'" class="cart-btn proceed-btn">Proceed to Checkout</button>
        <% } %>
        
        <% if (!isCartEmpty) { %>
           <form action="clearCart" method="post">
    <button type="submit">Clear Cart</button>
</form>
        <% } %>
        

    </div>
</body>
</html>
