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
 * The HomeController servlet handles requests for the Home page in the Elevate Home Decor web application.
 * It listens to the "/home" URL and forwards the request to the home page (home.jsp) for rendering.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     * This constructor calls the parent constructor (HttpServlet)
     */
    public HomeController() {
        super();
        
    }

    /**
	 * Handles HTTP GET requests to the /home URL.
	 * 
	 * This method is invoked when the client accesses the home page. It forwards the request to the "home.jsp"
	 * page for rendering
	 * 
	 * @param request  The HttpServletRequest object containing the request from the client.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs while forwarding the request or response.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward the request to home.jsp for displaying the home page
		request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request,response);
	}

	// Handles HTTP POST requests to the /home URL.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Reuses the doGet method for handling POST requests
		doGet(request, response);
	}

}
