package com.elevateHome.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Riya Bhatta
 * 
 * The ContactController servlet handles requests for the Contact Us page in the Elevate Home Decor web application.
 * It listens to the "/contact" URL and forwards the request to the contact page (contact.jsp) for rendering.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
     * Default constructor.
     * This constructor calls the parent constructor (HttpServlet)
     */
    public ContactController() {
        super();
        
    }

    /**
	 * Handles HTTP GET requests to the /contact URL.
	 * 
	 * This method is invoked when the client accesses the contact page. It simply forwards the request
	 * to the "contact.jsp" page for rendering. This page will display the contact form 
	 * 
	 * @param request  The HttpServletRequest object containing the request from the client.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs while forwarding the request or response.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forwards the request to contact.jsp for displaying the contact form 
		request.getRequestDispatcher("WEB-INF/pages/contact.jsp").forward(request,response);
	}

	// Handles HTTP GET requests to the /contact URL.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Reuses the doGet method for handling POST requests
		doGet(request, response);
	}

}
