package com.elevateHome.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Checks if a field is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Only letters (for first name and last name)
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }

    // Username starts with letter and contains only letters/numbers
    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }

    // Email validation
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // Role check for 'admin' or 'user'
    public static boolean isValidRole(String value) {
        return value != null && (value.equalsIgnoreCase("admin") || value.equalsIgnoreCase("user"));
    }

    // Strong password check: at least 8 chars, 1 capital, 1 number, 1 special char
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    // Password match
    public static boolean doPasswordsMatch(String password, String retypePassword) {
        return password != null && password.equals(retypePassword);
    }
    // Check for valid phone number format
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^[0-9]{10}$");
    }

}
