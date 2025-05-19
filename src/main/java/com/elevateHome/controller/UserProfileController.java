package com.elevateHome.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.elevateHome.util.SessionUtil;

/**
 * @author Riya Bhatta
 * 
 * UserProfileController is responsible for handling the request to
 * view the user profile page.
 * 
 * It retrieves the logged-in user's details from the session using SessionUtil,
 * sets them as request attributes, and forwards the request to the
 * userProfile.jsp page for display.
 * 
 * If the user is not logged in, it redirects them to the login page.
 * 
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userProfile" })
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     */
    public UserProfileController() {
        super();
       
    }

    /**
     * Handles GET requests to load and display user profile information.
     * 
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieves the current session
        HttpSession session = request.getSession();
        System.out.println("Get of user profile");
        
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message"); // Clear after displaying
        }
        
        // Fetches user details from the session
        String email = SessionUtil.getLoggedInUser(session);
        String firstName = SessionUtil.getUserFirstName(session);
        String lastName = SessionUtil.getUserLastName(session);
        String phoneNumber = SessionUtil.getUserPhoneNumber(session);
        String address = SessionUtil.getUserAddress(session);
        String role = SessionUtil.getUserRole(session);
        String profileImage = SessionUtil.getProfileImage(session);

        // Check if the user is logged in
        if (email != null) {
            // Setting the user details as request attributes
            request.setAttribute("email", email);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("address", address);
          request.setAttribute("role", role);
          request.setAttribute("profileImage", profileImage);

            // Forwards to the profile page
            request.getRequestDispatcher("WEB-INF/pages/userProfile.jsp").forward(request, response);
        } else {
            // If not logged in, redirects to login page
            response.sendRedirect("login.jsp");
        }
    }

	// Handles POST requests 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post of user profile");
		// Reuses the doGet method to handle POST requests to avoid duplicate code
		doGet(request, response);
	}

}
