package com.elevateHome.controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.elevateHome.model.UserModel;
import com.elevateHome.service.userDatabaseService;

/**
 * @author Riya Bhatta
 * 
 * UserController is a servlet that handles displaying all user records from the database.
 * 
 * It responds to GET requests at the "/user" URL pattern and forwards the list of users
 * to the userDatabase.jsp page for display. It relies on the userDatabaseService to fetch
 * all user entries stored in the system.
 * 
 * - userDatabaseService.getAllUsers() – Fetches a list of all registered users from the database.
 * - request.setAttribute() – Sets the list of users as a request attribute for use in the JSP.
 * - request.getRequestDispatcher().forward() – Forwards the request and response to the JSP view.
 *
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/user" })
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor.
     */
    public UserController() {
        super();
    }

    /**
     * Handles GET requests to display all users.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	// Fetchs all users from the database
            List<UserModel> users = userDatabaseService.getAllUsers();
            
            // Attachs user list to request to be displayed in JSP
            request.setAttribute("userList", users);
            
            // Forwards request to userDatabase.jsp page
            request.getRequestDispatcher("WEB-INF/pages/userDatabase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }


    /**
     * Handles POST requests by delegating to the doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Reuses the logic from doGet 
    	doGet(request, response); 
    }
}
