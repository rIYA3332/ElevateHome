package com.elevateHome.controller;

import com.elevateHome.util.SessionUtil;

import com.elevateHome.util.CookieUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Riya Bhatta
 * 
 * LogOutController class handles the logout functionality for the user.
 * When the user logs out, it performs actions like:
 * - Deleting cookies
 * - Clearing the session data.
 * - Redirecting the user to the login page.
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/logOut" })
public class LogOutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Constructor   
    public LogOutController() {
        super();
    }
    
    /**
     * doPost() method handles the HTTP POST request for logging out.
     * 
     * It deletes cookies, logs the user out by clearing the session, 
     * and redirects the user to the login page.
     * 
     * @param request  The HttpServletRequest object that contains the request.
     * @param response The HttpServletResponse object that will contain the response.
     * @throws ServletException if an exception occurs during the request processing.
     * @throws IOException if an I/O error occurs during the request processing.
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Deleting the 'role' cookie to clear the user's role stored in the cookies
    	CookieUtil.deleteCookie(response, "role");
    	
    	// Logging out the user by invalidating the session using SessionUtil
        SessionUtil.logout(request.getSession());
        
        // Redirecting the user to the login page after logging out
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
