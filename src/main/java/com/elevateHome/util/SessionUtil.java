package com.elevateHome.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    // Creating session and storing user information
    public static void createSession(HttpSession session, String email, String firstName, String lastName, String phoneNumber, String address, String profileImage) {
        session.setAttribute("email", email);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("phoneNumber", phoneNumber);
        session.setAttribute("address", address);
        session.setAttribute("profileImage", profileImage);
     
        session.setMaxInactiveInterval(30 * 60);  // Session timeout after 30 minutes of inactivity
    }

    // Checking if the user logged in
    public static boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("email") != null;
    }

    // Logout method to invalidate the session
    public static void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();  // Invalidate session
        }
    }

    // Method to get the email of the logged-in user from session
    public static String getLoggedInUser(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("email");
        }
        return null;
    }
 // Method to get other details of the logged-in user
    public static String getUserFirstName(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("firstName");
        }
        return null;
    }

    public static String getUserLastName(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("lastName");
        }
        return null;
    }

    public static String getUserPhoneNumber(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("phoneNumber");
        }
        return null;
    }

    public static String getUserAddress(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("address");
        }
        return null;
    }
    
 // NEW: Method to get the profile image filename
    public static String getProfileImage(HttpSession session) {
        return (session != null) ? (String) session.getAttribute("profileImage") : null;
    }

    // Method to get the role of the logged-in user (if needed for additional session management)
    public static String getUserRole(HttpSession session) {
        if (session != null) {
            return (String) session.getAttribute("role");
        }
        return null;
    }
    
}

