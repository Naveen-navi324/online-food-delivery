<%@page import="com.tap.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.tap.model.Restaurant, com.tap.dao.RestaurantDAO, com.tap.daoimplementation.RestaurantDAOImpl" %>

<%
//Get search query from request
String searchQuery = request.getParameter("searchQuery");
String user = (String) session.getAttribute("user"); // Get username from session

// Initialize DAO and fetch restaurant data
RestaurantDAO dao = new RestaurantDAOImpl();
List<Restaurant> restaurants;

if (searchQuery != null && !searchQuery.trim().isEmpty()) {
    restaurants = dao.searchRestaurants(searchQuery); // Fetch results based on search
} else {
    restaurants = dao.getAllRestaurants(); // Load all restaurants if no search
}

// Store the list in request scope
request.setAttribute("restaurants", restaurants);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Food Series</title>

<!-- External CSS -->
<link rel="stylesheet" href="homeStyle.css">
<!-- Font Awesome for Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body>
5
<div class="page-container"> <!-- Wrapper for full-page layout -->
    <header class="header">
        <div class="header-container">
            <div class="logo">Food Series</div>
            <nav class="nav"> 
                <a href="home.jsp"><i class="fas fa-home"></i> Home</a>
                <a href="logout.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </nav>
        </div>
    </header>

    <!-- Welcome Message -->
    <div class="container">
        <% if (user != null) { %>
            <h2 class="welcome-message">Welcome <%= user %>! </h2>
        <% } else { %>
            <h2 class="welcome-message">Welcome, Guest! Please <a href="login.jsp">Login</a></h2>
        <% } %>
    </div>

    <!-- Search Bar -->
    <div class="search-container">
        <form action="home.jsp" method="GET" class="search-form">
            <i class="fa fa-search search-icon"></i> 
            <input type="text" name="searchQuery" placeholder="Search for restaurants and food" class="search-input" value="<%= searchQuery != null ? searchQuery : "" %>">
            <button type="submit" class="search-button">Search</button>
        </form>
    </div>

    <!-- Restaurant List -->
    <div class="restaurant-section">
        <h2 class="section-title">
            <% if (searchQuery != null && !searchQuery.trim().isEmpty()) { %>
                Search results for "<%= searchQuery %>"
            <% } else { %>
                Restaurants near you
            <% } %>
        </h2>

        <div class="restaurant-grid">
            <% if (restaurants != null && !restaurants.isEmpty()) { %>
                <% for (Restaurant r : restaurants) { %>
                  <div class="restaurant-card" data-name="<%= r.getName().toLowerCase() %>" data-cuisine="<%= r.getCusineType().toLowerCase() %>">
                            <a href="menu?restaurantId=<%= r.getRestaurantId() %>" style="text-decoration: none; color: inherit;">
                                <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>" class="restaurant-image">
                                <div class="restaurant-info">
                                    <h2 class="restaurant-name"><%= r.getName() %></h2>
                                    <p class="restaurant-cuisine"><%= r.getCusineType() %></p>
                                    <div class="restaurant-metadata">
                                        <span class="rating"><i class="fas fa-star">★</i><%= r.getRating() %></span>

                                    </div>
                                </div>
                            </a>
                        </div>
                <% } %>
            <% } else { %>
                <p>No restaurants found.</p>
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
</div> <!-- Closing page-container -->



    <script>
        lucide.createIcons();

        document.getElementById('searchInput').addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const restaurantCards = document.querySelectorAll('.restaurant-card');
            
            restaurantCards.forEach(card => {
                const name = card.dataset.name;
                const cuisine = card.dataset.cuisine;
                card.style.display = (name.includes(searchTerm) || cuisine.includes(searchTerm)) ? 'block' : 'none';
            });
        });
    </script>

</body>
</html>