<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="confirmation.css">
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

    <main>
        <h1>Order Confirmed!</h1>
        <p>Thank you for your order. Your food will be delivered soon.</p>
        <a href="menu.jsp" class="btn">Continue Shopping</a>
    </main>
</body>
</html>
