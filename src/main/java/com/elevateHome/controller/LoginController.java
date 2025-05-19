package com.elevateHome.controller;

import com.elevateHome.model.UserModel;




import com.elevateHome.util.SessionUtil;
import com.elevateHome.util.CookieUtil;
import com.elevateHome.service.LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Riya Bhatta
 * 
 * The LoginController servlet handles user login functionality.
 * It authenticates user credentials, stores session data, and redirects users based on their role.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login", "/" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final LoginService loginService = new LoginService();
    
    /**
     * Default constructor
     */
    public LoginController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * 
     * When the login page is requested, this method forwards the request to the "login.jsp" page for rendering.
     * 
     * @param request  The HttpServletRequest object containing the request from the client.
     * @param response The HttpServletResponse object used to send a response back to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while forwarding the request or response.
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }
    /**
     * Handles HTTP POST requests.
     * 
     * When the login form is submitted, this method authenticates the user by checking their credentials.
     * If authentication is successful, a session is created with user details and appropriate redirection occurs based on user role.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Retrieves email and password from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a UserModel object to pass to the service
        UserModel user = new UserModel(0, "", "", email, "", password,"","","");

        // Call the service to check login credentials
        UserModel loggedInUser = loginService.loginUser(user);

        if (loggedInUser != null) {
            // Successfully authenticated user
            HttpSession session = request.getSession();
            session.setAttribute("user", loggedInUser);  // Store the full user object in session
            
            // Store the role for the session
            session.setAttribute("role", loggedInUser.getUserRole());
            session.setAttribute("profileImage", loggedInUser.getProfileImage());
         // Pass all necessary user details to create session
            SessionUtil.createSession(
                session,
                loggedInUser.getUserEmail(),
                loggedInUser.getUserFirstName(),
                loggedInUser.getUserLastName(),
               
                loggedInUser.getPhoneNumber(),
                loggedInUser.getAddress(),
                loggedInUser.getProfileImage()
                
            );
            
            // Checking if the user is an admin
            if ("admin".equals(loggedInUser.getUserRole())) {
            	
            	// for cookies
            	CookieUtil.addCookie(response, "role", "admin", 60 * 60);  // 60 mins

                // Redirects admin to the Admin Dashboard
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
                
            } else {
            	 CookieUtil.addCookie(response, "role", "user", 60 * 60);
            	 
                // Redirect regular user to the Home Page
                response.sendRedirect(request.getContextPath() + "/home");
            }
            
        } else {
            // If invalid credentials, navigate back to login
            System.out.println("Invalid credentials. Redirecting back to login.");
            request.setAttribute("error", "We couldn't find an account with that username. Try another, or get a new account.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}