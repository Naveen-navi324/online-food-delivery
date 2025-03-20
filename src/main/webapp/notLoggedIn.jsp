<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Not Logged In</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
</head>
<body>
    <div class="min-h-screen bg-gray-50">
        <header class="header">
            <div class="header-container">
                <div class="logo">Food Series</div>
                <nav class="nav"> 
                    <a href="home.jsp"><i class="fas fa-home"></i> </a>
                    <a href="logout.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>

                </nav>
            </div>
        </header>

    <div class="not-logged-in">
    <h2 class="warning">You are not logged in!</h2>
    <p class="message">Please log in to access the checkout page.</p>
    <a href="login.jsp" class="login-button">Go to Login Page</a>

    </div>
</body>
</html>
