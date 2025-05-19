package com.elevateHome.service;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.elevateHome.config.DBConfig;
import com.elevateHome.model.UserModel;
import com.elevateHome.util.PasswordUtil;

public class LoginService {

    private Connection conn;

    public LoginService() {
        try {
            conn = DBConfig.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserModel loginUser(UserModel userModel) {
        String email = userModel.getUserEmail();
        String inputPassword = userModel.getUserPassword();

        // Checking for special admin case
        if ("Admin123@gmail.com".equalsIgnoreCase(email)) {
            // Looking for admin user in DB
            String query = "SELECT user_id, user_first_name, user_last_name, user_email, user_password, user_phone_number, user_address, profile_image FROM user WHERE user_email = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    String encryptedPassword = result.getString("user_password");
                    String decryptedPassword = PasswordUtil.decrypt(encryptedPassword, email);

                    if ("Admin123@".equals(inputPassword) && inputPassword.equals(decryptedPassword)) {
                        return new UserModel(
                            result.getInt("user_id"),
                            result.getString("user_first_name"),
                            result.getString("user_last_name"),
                            result.getString("user_email"),
                            "admin",  // Hardcoded admin role
                            result.getString("user_password"),
                            result.getString("user_phone_number"),
                            result.getString("user_address"),
                            result.getString("profile_image")
                        );
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null; // If admin password didn't match
        }

        // For all other users (normal users)
        String query = "SELECT user_id, user_first_name, user_last_name, user_email, user_password, user_phone_number, user_address, profile_image FROM user WHERE user_email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String encryptedPassword = result.getString("user_password");
                String decryptedPassword = PasswordUtil.decrypt(encryptedPassword, email);

                if (inputPassword.equals(decryptedPassword)) {
                    return new UserModel(
                        result.getInt("user_id"),
                        result.getString("user_first_name"),
                        result.getString("user_last_name"),
                        result.getString("user_email"),
                        "user",  // Default role for normal users
                        result.getString("user_password"),
                        result.getString("user_phone_number"),
                        result.getString("user_address"),
                        result.getString("profile_image")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
    }
    
}
