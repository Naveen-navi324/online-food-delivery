<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
         
    <div class="container">
        <h2>Sign Up</h2>
        <form action="<%= request.getContextPath() %>/RegisterServlet" method="post">
            <label>Name:</label>
            <input type="text" name="name" required><br><br>
            
            <label>Username:</label>
            <input type="text" name="username" required><br><br>
            
            <label>Password:</label>
            <input type="password" name="password" required><br><br>
            
            <label>Email:</label>
            <input type="email" name="email" required><br><br>
            
            <label>Address:</label>
            <input type="text" name="address" required><br><br>
            
            <label>Phone:</label>
            <input type="tel" name="phone" required><br><br>
            
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
