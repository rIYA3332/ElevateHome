package com.elevateHome.controller;

import com.elevateHome.service.UserService;

import com.elevateHome.util.ImageUtil;
import com.elevateHome.util.PasswordUtil;
import com.elevateHome.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @author Riya Bhatta
 * 
 * UpdateProfileController handles POST requests to update a logged-in user's profile.
 * 
 * It checks the session to ensure the user is logged in, retrieves updated phone number
 * and address from the request, calls the UserService to update the user in the database,
 * and then forwards the result (success or error) back to the user profile JSP.
 * 
 * 
 * 
 */

@WebServlet("/UpdateProfileController")
@MultipartConfig
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * Processes the profile update form submission.
     *
     * @param request  the HTTP request containing updated user details
     * @param response the HTTP response used to redirect or forward the user
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Retrieves the current HTTP session; false means do not create if it doesn't exist
        HttpSession session = request.getSession(false);
        System.out.println("Reached here");        
        
        // Redirects to login if session is null or user not logged in
        if (session == null || SessionUtil.getLoggedInUser(session) == null) {
            response.sendRedirect("login.jsp");
            System.out.println("Invalid login status") ;
            return;
        }
        
        // Gets the logged-in user's email from session
        String email = SessionUtil.getLoggedInUser(session);
        //new
     // Get uploaded image
        Part imagePart = request.getPart("profileImage");
        String profileImage = null;
        
        if (imagePart != null && imagePart.getSize() > 0) {
            String uploadDir = getServletContext().getRealPath("/resources/images/system/user_images"); // Match RegisterController's path
            profileImage = ImageUtil.saveImage(imagePart, uploadDir);
        }
        //
        // Gets updated form values
        String newPhone = request.getParameter("newPhoneNumber");
        String newAddress = request.getParameter("newAddress");
        String newPassword = request.getParameter("newPassword");
        
     // Encrypting the password
        String encryptedPassword = PasswordUtil.encrypt(email, newPassword);
        
        UserService userService = new UserService();
        boolean updated = userService.updateUserProfile(email, newPhone, newAddress, encryptedPassword, profileImage);
     
        if (updated) {
            // Updating session attributes
        	session.setAttribute("profileImage", profileImage);
            session.setAttribute("phoneNumber", newPhone);
            session.setAttribute("address", newAddress);
            session.setAttribute("password", newPassword);
            request.setAttribute("message", "Profile updated successfully.");
         // Redirecting instead of forward
            response.sendRedirect(request.getContextPath() + "/userProfile");
            return; 
            
        } else {
        	// Notifing user of failure
        	System.out.println("Failed update here"); 
            request.setAttribute("error", "Failed to update profile.");
        }
        
        // Forwarding back to user profile page
        request.getRequestDispatcher("WEB-INF/pages/userProfile.jsp").forward(request, response);
    }
}
