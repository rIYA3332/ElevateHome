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
 * AboutUsController is a servlet responsible for handling requests to the "About Us" page.
 * This servlet is mapped to the URL pattern "/aboutUs" and is responsible for forwarding requests
 *  to the corresponding JSP page for rendering. It supports both GET and POST requests.
*/


@WebServlet(asyncSupported = true, urlPatterns = { "/aboutUs" })
public class AboutUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     * This constructor calls the parent constructor from HttpServlet.
     */
    public AboutUsController() {
        super();
        
    }

    /**
	 * Handles GET requests to the /aboutUs URL.
	 * This method is triggered when the client sends an HTTP GET request, clicking on the link
	 * It forwards the request to the `aboutUs.jsp` page for rendering the view.
	 * 
	 * @param request The HttpServletRequest object that contains client request information.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException If there is a servlet-specific error.
	 * @throws IOException If there is an I/O error during request forwarding.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/pages/aboutUs.jsp").forward(request,response);
	}

	// Handles POST requests to the /aboutUs URL.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Reuses the doGet method to handle POST requests the same way
		doGet(request, response);
	}

}
