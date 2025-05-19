package com.elevateHome.config;

import java.sql.Connection;

import java.sql.DriverManager;

public class DBConfig{
	/**
	 * @author Riya Bhatta
	 * 
     * This method is responsible for establishing a connection to the MySQL database.
     * It uses the JDBC driver to connect to a MySQL database running on localhost.
     * The connection is created using the credentials provided in the method (username and password).
     * If the connection is successful, it returns the connection object; otherwise, it throws an exception.
     * 
     * @return Connection - The active connection to the database.
     * @throws Exception If an error occurs while connecting to the database, an exception is thrown.
     */
	public static Connection getConnection() throws Exception {
	    try {
	    	// The URL specifies the location of the MySQL database and its name.
           
	        String url = "jdbc:mysql://localhost:3306/elevatehomedecor";
	        String username = "root"; // Database username
	        String password = ""; // Database password
	        
	        // Used to log the connection attempt to monitor when the connection is initiated
	        System.out.println("Attempting to connect to database...");
	        
	        // This is necessary for establishing a connection with the database
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // This returns a Connection object that can be used to interact with the database
	        Connection con = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected successfully!");
	        return con;
	    } catch (Exception e) {
	        // Used to log the error details
	    	// If an error occurs during the connection process, it will be caught here
	        System.err.println("Error connecting to database: " + e.getMessage());
	        e.printStackTrace();
	        throw e;  // Rethrow the exception so that it can be handled further up the stack.
	        }
	}


}
