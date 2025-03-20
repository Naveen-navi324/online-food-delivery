<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant logout - Food Series</title>
    
    <link rel="stylesheet" href="logout.css"> 
      <!-- Linking to external CSS -->
</head>
<body>
    <div class="min-h-screen bg-gray-50">
    <header class="header">
    <div class="logo">Food Series</div>
    
</header>
</div>
    <h1>Are you sure you want to logout?</h1>
    
    <form action="logout" method="get">
        <button type="submit">Click To logout</button>
    </form>

    <a href="main.jsp"> Thank You</a>
</body>
</html>
