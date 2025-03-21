package com.tap.daoimplementation;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO{
	
	private static final String INSERT_USER_QUERY = "INSERT into `user` (`name`,`username`,`password`,`email`,`phone`,`address`,`role`)values (?,?,?,?,?,?,?)";
	private static final String GET_USER_QUERY = "SELECT * FROM `user` WHERE `userId`= ?";
	private static final String UPDATE_USER_QUERY = "UPDATE `USER` SET `name` = ? `password` = ? `eamil` = ? `phone` = ? `address` = ? `role` = ? ";
	private static final String DELETE_USER_QUERY = "DELETE FROM `user` WHERE `userId` = ? ";
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM `user`";
	
	
	@Override
	public void addUser(User user)  {
	
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USER_QUERY) ) {
			
		    
		    prepareStatement.setString(1,user.getName() );
		    prepareStatement.setString(2,user.getUsername() );
		    prepareStatement.setString(3,user.getPassword() );
		    prepareStatement.setString(4,user.getEmail() );
		    prepareStatement.setString(5,user.getPhone() );
		    prepareStatement.setString(6,user.getAddress() );
		    prepareStatement.setString(7,user.getRole() );
		    
		   int executeUpdate  =  prepareStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public User getUser(int userId) {
		
		
		    User user = null;
		
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_QUERY);)
		{
		    prepareStatement.setInt(1, userId);
		    ResultSet res  = prepareStatement.executeQuery();
		    
		    user = extractUser(res);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return user;
	}
	
	@Override
	public void updateUser(User user) {
	  
		   Connection connection = null;
		   PreparedStatement prepareStatement;
	  
	  try {
		  connection  = DBConnection.getConnection();
		  prepareStatement = connection.prepareStatement(UPDATE_USER_QUERY);
		  
		  prepareStatement.setString(1,user.getName() );
		  prepareStatement.setString(2,user.getPassword() );
		  prepareStatement.setString(1,user.getPhone() );
		  prepareStatement.setString(1,user.getAddress() );
		  prepareStatement.setString(1,user.getRole() );
		  
		  prepareStatement.executeUpdate();
		  
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	    
 }

	@Override
	public void deleteUser(int userId) {
		
		
		     Connection connection = null;
		     PreparedStatement prepareStatement;
		
		try {
			connection  = DBConnection.getConnection();
			prepareStatement = connection.prepareStatement(DELETE_USER_QUERY);
		
			prepareStatement.setInt(1,  userId);
			
			prepareStatement.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public List<User> getAllUsers() {
		
        
        ArrayList<User> usersList = new ArrayList<User>();
		
        try {
        	 Connection connection  = DBConnection.getConnection();
        	 Statement statement = connection.createStatement();
        	 
			 ResultSet res = statement.executeQuery(GET_ALL_USERS_QUERY);
			
			while (res.next()) {
				    
			       User user = extractUser(res);
			       
			       usersList.add(user);
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
		return usersList;
	}
	
	
	
	
	
	
	
	
	public String validateUser(String email, String password) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = DBConnection.getConnection();  // Ensure this method correctly establishes a DB connection
	        String sql = "SELECT name FROM user WHERE email = ? AND password = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, email);
	        ps.setString(2, password);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getString("name"); // Return the user's name if authentication is successful
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Ensure all resources are closed
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return null; // Return null if authentication fails
	}

	public String getUserNameByEmail(String email) {
	    String name = null;
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT name FROM user WHERE email = ?")) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            name = rs.getString("name"); // Retrieve name from DB
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return name;
	}


	
	
	
	

     User extractUser(ResultSet res) throws SQLException
    {
    	    int userId = res.getInt("userId"); 
		    String name = res.getString("name");
		    String username = res.getString("username");
		    String password = res.getString("password");
		    String email = res.getString("email");
		    String phone =res.getString("phone");
		    String address = res.getString("address");
		    String role = res.getString("role");
		        
		    User user = new User(userId, name, username, password, email, phone, address, role, null, null);
            
		    return user; 
    }
}
