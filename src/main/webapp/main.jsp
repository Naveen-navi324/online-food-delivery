<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Food Delivery</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="navbar">
        <a href="login.jsp">
            <i class="fas fa-sign-in-alt"></i> Login
        </a>
        <a href="register.jsp">
            <i class="fas fa-user-plus"></i> Signup
        </a>
    </div>
     <div class="navbar">
    <a href="home.jsp">Home</a> <!-- Add this link to navigate to home.jsp -->
    <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
    <a href="register.jsp"><i class="fas fa-user-plus"></i> Signup</a>
</div>
     
     <section class ="main">
     <h2 class="heading_1"> enjoy our <br> delicious meal</h2></section>
    <div class="container">
        <h1>Food series</h1>
	<form action="home.jsp" method="GET">
            <button type="submit">Search Restaurants</button>
        </form>
    </div>
  

</body>
</html>
