<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.tap.model.*" %>
<%
    // Check if user is logged in
    String user = (String) session.getAttribute("user"); // Assuming "user" is stored in session
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Food Series - Checkout</title>
    <link rel="stylesheet" href="CheckOut.css">
</head>
<body>
<div class="min-h-screen bg-gray-50">
    <header class="header">
    <div class="logo">Food Series</div>
    <nav class="nav">
        <a href="home.jsp">
            <i class="fas fa-home"></i> Home
        </a>
        <a href="logout-action.jsp">
            <i class="fas fa-sign-out-alt"></i>Logout
        </a>
    </nav>
</header>

  </div>
    <%
        if (user == null) { 
    %>
        <div class="container">
            <p class="warning">You are not logged in!</p>
            <p class="message">Please log in to access the checkout page.</p>
            <a href="login.jsp" class="login-button">Go to Login Page</a>
        </div>
    <%
        } else { 
    %>
        <main>
            <h1>Checkout</h1>

            <%
                // Check if cart is empty
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null || cart.getCartItems().isEmpty()) {
                    response.sendRedirect("cart.jsp"); // Redirect to cart if empty
                    return;
                }
                Map<Integer, CartItem> cartItems = cart.getCartItems();
            %>

            <!-- Display Cart Items -->
            <div class="cart-summary">
                <h2>Order Summary</h2>
                <% for (CartItem item : cartItems.values()) { %>
                    <div class="cart-item">
                        <p><strong><%= item.getName() %></strong></p>
                        <p>Quantity: <%= item.getQuantity() %></p>
                        <p>Price: Rs. <%= item.getPrice() %></p>
                        <p>Total: Rs. <%= item.getQuantity() * item.getPrice() %></p>
                    </div>
                <% } %>
                <p><strong>Grand Total: Rs. <%= cart.getTotalPrice() %></strong></p>
            </div>

            <!-- Checkout Form -->
            <form action="process-checkout" method="POST">
                <div class="form-section">
                    <h2>Address Details</h2>
                    <div class="form-group">
                        <label for="fullAddress">Full Address:</label>
                        <input type="text" id="fullAddress" name="fullAddress" placeholder="Enter your full address" required>
                    </div>
                </div>

                <div class="form-section">
                    <h2>Payment Mode</h2>
                    <div class="form-group">
                        <label for="paymentMode">Select Payment Mode:</label>
                        <select id="paymentMode" name="paymentMode" required>
                            <option value="">-- Select Payment Mode --</option>
                            <option value="card">Credit/Debit Card</option>
                            <option value="upi">UPI</option>
                            <option value="netbanking">Net Banking</option>
                            <option value="cod">Cash on Delivery</option>
                        </select>
                    </div>
                    <button type="submit" class="pay-button">Confirm & Pay</button>
                </div>
            </form>
        </main>
    <%
        } 
    %>
</body>
</html>