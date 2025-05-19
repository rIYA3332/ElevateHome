package com.elevateHome.service;


import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.elevateHome.config.DBConfig;
import com.elevateHome.model.UserModel;

public class register {
	public boolean insert(UserModel user) throws Exception{
		try {
			System.out.println("Trying to connect...");
			Connection con = DBConfig.getConnection();
			System.out.println("Connected!");

		
		String query="insert into user(user_id, user_first_name, user_last_name, user_email, user_role, user_password,user_phone_number,user_address,profile_image) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, user.getUserId());
		ps.setString(2, user.getUserFirstName());
		ps.setString(3, user.getUserLastName());
		
		
		ps.setString(4, user.getUserEmail());
		ps.setString(5, user.getUserRole());
		ps.setString(6, user.getUserPassword());
		ps.setString(7, user.getPhoneNumber());
        ps.setString(8, user.getAddress());
        ps.setString(9, user.getProfileImage());
        
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
	}
		 public boolean emailExists(String email) throws Exception {
		        Connection con = DBConfig.getConnection();
		        String query = "SELECT user_email FROM user WHERE user_email = ?";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, email);
		        ResultSet rs = ps.executeQuery();
		        boolean exists = rs.next();
		        con.close();
		        return exists;
		    }

		    public boolean phoneNumberExists(String phoneNumber) throws Exception {
		        Connection con = DBConfig.getConnection();
		        String query = "SELECT user_phone_number FROM user WHERE user_phone_number = ?";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, phoneNumber);
		        ResultSet rs = ps.executeQuery();
		        boolean exists = rs.next();
		        con.close();
		        return exists;
	}
} 