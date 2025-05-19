package com.elevateHome.service;

import com.elevateHome.model.UserModel;


import com.elevateHome.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class userDatabaseService {
    public static List<UserModel> getAllUsers() throws Exception {
        List<UserModel> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	UserModel user = new UserModel(
            		    rs.getInt("user_id"),
            		    rs.getString("user_first_name"),  
            		    rs.getString("user_last_name"),   
            		    rs.getString("user_email"),
            		    rs.getString("user_role"),
            		    rs.getString("user_password"),
            		    rs.getString("user_phone_number"),
            		    rs.getString("user_address"),
            		    rs.getString("profile_image")
            		);

                userList.add(user);
            }
        
        } catch (Exception e) {
            e.printStackTrace(); 
            throw e;
        }
        return userList;
    }
}
