<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <!-- Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    
    <!-- FontAwesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body class="min-h-screen flex flex-col bg-gray-200">
    
    <!-- Header -->
    <header class="header bg-blue-600 text-white py-4 px-6 shadow-md">
        <div class="max-w-6xl mx-auto flex justify-between items-center">
            <div class="logo text-2xl font-bold">Food Series</div>
            <nav class="nav">
                <a href="home.jsp" class="text-white text-lg mx-2 hover:text-gray-300">
                    <i class="fas fa-home"></i> Home
                </a>
                
            </nav>
        </div>
    </header>

    <!-- Login Form Container -->
    <div class="flex flex-grow items-center justify-center">
        <div class="login-container bg-white p-8 rounded-lg shadow-lg w-96 transition-transform transform hover:scale-105">
            <h2 class="text-2xl font-bold text-center mb-6">Sign In</h2>

            <form action="login" method="post">
                <div class="mb-4">
                    <label class="block text-sm font-medium text-gray-700">Email</label>
                    <input type="email" name="email" required 
                        class="w-full p-3 border border-gray-300 rounded-md focus:border-blue-500 focus:ring-2 focus:ring-blue-200">
                </div>

                <div class="mb-4">
                    <label class="block text-sm font-medium text-gray-700">Password</label>
                    <input type="password" name="password" required 
                        class="w-full p-3 border border-gray-300 rounded-md focus:border-blue-500 focus:ring-2 focus:ring-blue-200">
                </div>

                <button type="submit" 
                    class="w-full bg-blue-600 text-white py-2 rounded-md font-semibold hover:bg-blue-700 transition-transform transform hover:scale-105">
                    Sign In
                </button>
            </form>

            <% if (request.getAttribute("errorMessage") != null) { %>
                <p class="text-red-500 text-sm mt-3"><%= request.getAttribute("errorMessage") %></p>
            <% } %>

            <p class="mt-4 text-sm text-center">
                Don't have an account? <a href="register.jsp" class="text-blue-600 font-semibold hover:underline">Sign Up</a>
            </p>
        </div>
    </div>

</body>
</html>
