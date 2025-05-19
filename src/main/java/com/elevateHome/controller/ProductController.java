package com.elevateHome.controller;

import com.elevateHome.model.ProductModel;

import com.elevateHome.service.ProductDatabaseService;
import com.elevateHome.util.ImageUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
/**
 * @author Riya Bhatta
 * 
 * ProductController class handles product-related actions such as 
 * displaying product information, adding, updating, and deleting products.
 * 
 */

@WebServlet("/product")
@MultipartConfig
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor
     */
    public ProductController() {
        super();
    }
 // Initialize this once for all requests
    private String getUploadDirectory() {
        return getServletContext().getRealPath("/resources/images/system/product_images");
    }

    /**
     * Handles GET requests for displaying the product list, with optional search functionality.
     * 
     * @param request The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If the request processing fails.
     * @throws IOException If an input or output error occurs during request handling.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	    throws ServletException, IOException {
    	    try {
    	        String searchTerm = request.getParameter("search"); // Get search term
    	        List<ProductModel> products = ProductDatabaseService.getAllProducts();

    	        // Highlights & prioritizes products that match the search term
    	        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
    	            final String searchLower = searchTerm.trim().toLowerCase();
    	            
    	         // Sorts products to display matching ones first
    	            products.sort((p1, p2) -> {
    	                boolean p1Match = p1.getProduct_name().toLowerCase().contains(searchLower);
    	                boolean p2Match = p2.getProduct_name().toLowerCase().contains(searchLower);
    	                return Boolean.compare(p2Match, p1Match); // Matches come first
    	            });
    	         // Sets the search term in the request for display
    	            request.setAttribute("searchTerm", searchTerm);
    	        }

    	        request.setAttribute("productList", products);
    	        request.getRequestDispatcher("WEB-INF/pages/productDatabase.jsp").forward(request, response);
    	    } catch (Exception e) {
    	    	// Handles any errors that occur during database interaction
    	        e.printStackTrace();
    	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
    	    }
    	}

    /**
     * Handles POST requests for different product-related actions (create, update, delete, etc.).
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            try {
            	// Performs different actions based on the value of the action parameter
                switch (action) {
                    case "create":
                        createProduct(request, response); // creates a new product
                        break;
                    case "update":
                        enterEditMode(request, response);// edit mode for a selected product
                        break;
                    case "confirmUpdate":
                        confirmUpdateProduct(request, response); // Confirm update of the product
                        break;
                    case "cancelEdit":
                        cancelEditMode(request, response); // Cancel edit and return to product list
                        break;
                    case "delete":
                        deleteProduct(request, response); // Delete a product
                        break;
                    default:
                        doGet(request, response); // Default to GET request handling
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the request: " + e.getMessage());
            }
        } else {
            doGet(request, response);
        }
    }
    /**
     * Handles the creation of a new product.
     */
    
    
    private void createProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Part imagePart = request.getPart("product_image");
            String productImage = null;
            
            if (imagePart != null && imagePart.getSize() > 0) {
                String uploadDir = getUploadDirectory();
                productImage = ImageUtil.saveImage(imagePart, uploadDir);
            }

            ProductModel product = new ProductModel(
                    0, 
                    request.getParameter("product_name"),
                    request.getParameter("product_brand"),
                    Double.parseDouble(request.getParameter("product_price")),
                    Integer.parseInt(request.getParameter("product_sales")),
                    Integer.parseInt(request.getParameter("product_stock")),
                    request.getParameter("product_status"),
                    productImage
            );
            
            ProductDatabaseService.addProduct(product);
            response.sendRedirect("product");
        } catch (Exception e) {
            handleError(request, response, e, "Error creating product: ");
        }
    }

    /**
     * Handles the update feature of a selected product.
     */
    private void enterEditMode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String editId = request.getParameter("product_id");
            List<ProductModel> products = ProductDatabaseService.getAllProducts();  // Fetchs all products from the databas
            // Sets the list of products and the product ID to edit
            request.setAttribute("productList", products);
            request.setAttribute("editId", editId);
            
            // Forwards request to the JSP page for editing
            request.getRequestDispatcher("WEB-INF/pages/productDatabase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error entering edit mode: " + e.getMessage());
        }
    }

    /**
     * Confirm the update of a product's details.
     */
    private void confirmUpdateProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String existingImage = request.getParameter("existing_product_image");
            Part imagePart = request.getPart("product_image");
            String productImage = existingImage;

            if (imagePart != null && imagePart.getSize() > 0) {
                String uploadDir = getUploadDirectory();
                productImage = ImageUtil.saveImage(imagePart, uploadDir);
            }

            ProductModel product = new ProductModel(
                    Integer.parseInt(request.getParameter("product_id")),
                    request.getParameter("product_name"),
                    request.getParameter("product_brand"),
                    Double.parseDouble(request.getParameter("product_price")),
                    Integer.parseInt(request.getParameter("product_sales")),
                    Integer.parseInt(request.getParameter("product_stock")),
                    request.getParameter("product_status"),
                    productImage
            );
            
            ProductDatabaseService.updateProduct(product);
            response.sendRedirect("product");
        } catch (Exception e) {
            handleError(request, response, e, "Error updating product: ");
        }
    }
    
    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e, String message) 
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("error", message + e.getMessage());
        doGet(request, response);
    }


    /**
     * Cancels the edit mode and return to the product list.
     */
    private void cancelEditMode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("product"); // Redirects to product list
    }
    
    /**
     * Deletes a product from the database.
     */
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("product_id")); // Uses the product ID to delete
            ProductDatabaseService.deleteProduct(id);
            response.sendRedirect("product"); // Redirects to the product list
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting product: " + e.getMessage());
        }
    }
}
