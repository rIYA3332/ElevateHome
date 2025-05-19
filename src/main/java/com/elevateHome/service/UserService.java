package com.elevateHome.service;

import com.elevateHome.config.DBConfig;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

    public boolean updateUserProfile(String email, String phoneNumber, String address, String password, String profileImage) {
        String query = "UPDATE user SET user_phone_number = ?, user_address = ?, user_password = ?, profile_image = ? WHERE user_email = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, phoneNumber);
            ps.setString(2, address);
            ps.setString(3,password);
            ps.setString(4, profileImage); 
            ps.setString(5, email);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
