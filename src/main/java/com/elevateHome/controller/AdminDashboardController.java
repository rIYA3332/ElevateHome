package com.elevateHome.controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.elevateHome.service.AdminDashboardService;


/**
 * @author Riya Bhatta
 * 
 * The AdminDashboardController servlet handles requests for the admin dashboard page.
 * It listens to the "/adminDashboard" URL and retrieves various statistics such as total users,
 * total products, low stock products, and total revenue to display on the admin dashboard.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminDashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     * This constructor calls the parent constructor (HttpServlet)
     */
    public AdminDashboardController() {
        super();
        
    }

    /**
	 * Handles HTTP GET requests to the /adminDashboard URL.
	 * 
	 * This method is invoked when the client accesses the admin dashboard. It retrieves various 
	 * statistics and data necessary for the admin dashboard page, 
	 * After gathering the data, the method formats the revenue and sets attributes to be accessed 
	 * in the JSP view. If an error occurs while retrieving data, an error message is forwarded to the JSP.
	 * 
	 * @param request  The HttpServletRequest object containing the request from the client.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs while forwarding the request or response.
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	    throws ServletException, IOException {
    	    
    	    try {
    	    	 // Retrieves data for the dashboard using AdminDashboardService
    	        int totalUsers = AdminDashboardService.getTotalUserCount();
    	        int totalProducts = AdminDashboardService.getTotalProductCount();
    	        int lowStockCount = AdminDashboardService.getLowStockCount();
    	        double totalRevenue = AdminDashboardService.getTotalRevenue();
    	        
    	     // Retrieves data for tables (top-selling and low stock products)
    	        String[][] topProducts = AdminDashboardService.getTopSellingProducts(5);
    	        String[] lowStockProducts = AdminDashboardService.getLowStockProductNames(5);
    	        
    	        // Format revenue (e.g., "200k")
    	        String formattedRevenue = formatRevenue(totalRevenue);
    	        
    	        // Setting attributes for JSP
    	        request.setAttribute("totalUsers", totalUsers);
    	        request.setAttribute("totalProducts", totalProducts);
    	        request.setAttribute("lowStockCount", lowStockCount);
    	        request.setAttribute("totalRevenue", formattedRevenue);
    	        request.setAttribute("topProducts", topProducts);
    	        request.setAttribute("lowStockProducts", lowStockProducts);
    	     // Forwards the request and response to the adminDashboard.jsp page
    	        request.getRequestDispatcher("WEB-INF/pages/adminDashboard.jsp").forward(request, response);
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        request.setAttribute("error", "Error loading dashboard data");
    	        request.getRequestDispatcher("WEB-INF/pages/adminDashboard.jsp").forward(request, response);
    	    }
    	}
    /**
     * Helper method to format the total revenue into a readable string, eg, in thosands or millions
     * 
     * @param revenue The total revenue as a double value.
     * @return A formatted string representing the revenue in a human readable form.
     */
    	private String formatRevenue(double revenue) {
    		// If revenue is in millions, it formats it as "1.2m"
    	    if (revenue >= 1000000) {
    	        return String.format("%.1fm", revenue/1000000);
    	    } else if (revenue >= 1000) {
    	        return String.format("%.1fk", revenue/1000);
    	    }
    	    return String.format("%.0f", revenue);
    	}

	// Handles HTTP POST requests to the /adminDashboard URL.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Reusing doGet method for handling POST requests
		doGet(request, response);
	}

}
