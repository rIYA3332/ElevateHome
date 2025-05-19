package com.elevateHome.controller;

import com.elevateHome.model.CategoryModel;

import com.elevateHome.service.CategoryDatabaseService;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Riya Bhatta
 * 
 * The CategoryController servlet handles requests for the category page in the Elevate Home Decor web application.
 * It listens to the "/category" URL and forwards the request to the category database page for rendering.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/category" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     * This constructor calls the parent constructor (HttpServlet)
     */
    public CategoryController() {
        super();
        
    }

    /**
	 * Handles HTTP GET requests to the /category URL.
	 * 
	 * This method is invoked when the client accesses the category page. It simply forwards the request
	 * to the "categoryDatabase.jsp" page for rendering. This page will display the category data or a list 
	 * of categories from the database, depending on how it's set up in the JSP.
	 * 
	 * @param request  The HttpServletRequest object containing the request from the client.
	 * @param response The HttpServletResponse object used to send a response back to the client.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs while forwarding the request or response.
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchTerm = request.getParameter("search");
            List<CategoryModel> categories = CategoryDatabaseService.getAllCategories();

            if (searchTerm != null && !searchTerm.isEmpty()) {
                final String searchLower = searchTerm.toLowerCase();
                categories.sort((c1, c2) -> 
                    Boolean.compare(
                        c2.getCategory_name().toLowerCase().contains(searchLower),
                        c1.getCategory_name().toLowerCase().contains(searchLower)
                    ));
                request.setAttribute("searchTerm", searchTerm);
            }

            request.setAttribute("categoryList", categories);
            request.getRequestDispatcher("WEB-INF/pages/categoryDatabase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    createCategory(request, response);
                    break;
                case "update":
                    enterEditMode(request, response);
                    break;
                case "confirmUpdate":
                    confirmUpdate(request, response);
                    break;
                case "cancelEdit":
                    cancelEdit(request, response);
                    break;
                case "delete":
                    deleteCategory(request, response);
                    break;
                default:
                    doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CategoryModel category = new CategoryModel(
            0,
            request.getParameter("category_name"),
            request.getParameter("product_list"),
            Integer.parseInt(request.getParameter("total_product_items"))
        );
        CategoryDatabaseService.addCategory(category);
        response.sendRedirect("category");
    }

    private void enterEditMode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("editId", request.getParameter("category_id"));
        doGet(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CategoryModel category = new CategoryModel(
            Integer.parseInt(request.getParameter("category_id")),
            request.getParameter("category_name"),
            request.getParameter("product_list"),
            Integer.parseInt(request.getParameter("total_product_items"))
        );
        CategoryDatabaseService.updateCategory(category);
        response.sendRedirect("category");
    }

    private void cancelEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("category");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        CategoryDatabaseService.deleteCategory(categoryId);
        response.sendRedirect("category");
    }
}
