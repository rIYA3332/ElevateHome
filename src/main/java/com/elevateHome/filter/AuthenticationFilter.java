package com.elevateHome.filter;

import com.elevateHome.model.UserModel;




import com.elevateHome.util.CookieUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Riya Bhatta
 * 
 * AuthenticationFilter is a filter used to check whether a user is logged in and has appropriate access to certain pages.
 * It checks for valid user sessions and role-based redirection (admin vs. regular user).
 * The filter applies to all URLs and checks session validity before processing further requests.
 * 
 * It redirects users to the login page if not logged in, and restricts access to admin-only pages if the user is not an admin.
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    // URL paths for redirection and access control
    private static final String LOGIN = "/login";
    private static final String HOME = "/home";
    private static final String DASHBOARD = "/adminDashboard";
    private static final String ABOUT = "/aboutUs";
    private static final String CONTACT = "/contact";
    private static final String USER_PROFILE = "/userProfile";
    private static final String USER_PROFILE_UPDATE = "/UpdateProfileController";
    // Admin-only paths
    private static final String USER_MANAGEMENT = "/user";
    private static final String PRODUCT_MANAGEMENT = "/product";
    private static final String CATEGORY_MANAGEMENT = "/category";

    /**
    * 
    * @param filterConfig The configuration for the filter (not used in this case).
    * @throws ServletException If an error occurs during filter initialization.
    */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    /**
     * This method intercepts the requests and performs the authentication check before allowing access to resources.
     * It checks if the user is logged in and whether they have the right role (admin or user) to access the requested page.
     * If the user is not logged in or doesn't have sufficient permissions, they are redirected to the login page or appropriate page.
     *
     * @param request  The ServletRequest object that contains the request data from the client.
     * @param response The ServletResponse object used to return data to the client.
     * @param chain    The FilterChain object that passes the request and response to the next filter in the chain.
     * 
     * @throws IOException If an input or output error occurs while handling the request.
     * @throws ServletException If an error occurs while processing the request in the filter.
     * 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        UserModel user = (session != null) ? (UserModel) session.getAttribute("user") : null;

        boolean isLoginPage = uri.endsWith("login.jsp") || uri.endsWith("/login");
        boolean isPublicPage = uri.contains("/resources/") || uri.endsWith("/register") || uri.endsWith("register.jsp") 
                            || uri.endsWith("/logOut") || uri.endsWith("/logout");
        
        // If the requested URI is for login, public pages, or static assets, continues the filter chain without restriction
        if (isLoginPage || isPublicPage || uri.endsWith(".css") || uri.endsWith(".js") || 
            uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".jpeg") || uri.endsWith(".gif")) {
            chain.doFilter(request, response);
            return;
        }
        
     // If the user is logged in, proceeds with checking their role
        if (user != null) {
        	
        	// Gets the user's role either from a cookie 
            Cookie roleCookie = CookieUtil.getCookie(req, "role");
            String userRole = (roleCookie != null) ? roleCookie.getValue() : null;
            
            // If the role is not found in the cookie, it checks the session
            if (userRole == null && session != null) {
                userRole = (String) session.getAttribute("role");
            }
            
            // Role-based access control
            if ("admin".equals(userRole)) {
                // Admin allowed pages
                if (uri.endsWith(DASHBOARD) || uri.startsWith(req.getContextPath() + USER_MANAGEMENT)
                    || uri.startsWith(req.getContextPath() + PRODUCT_MANAGEMENT)
                    || uri.startsWith(req.getContextPath() + CATEGORY_MANAGEMENT)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(req.getContextPath() + DASHBOARD);
                }
            } else if ("user".equals(userRole)) {
                // Normal user allowed pages
                if (uri.endsWith(HOME) || uri.endsWith(ABOUT) || uri.endsWith(CONTACT) || uri.endsWith(USER_PROFILE)
                		|| uri.endsWith(USER_PROFILE_UPDATE)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(req.getContextPath() + HOME);
                }
            }
        } else {
        	// If no user is logged in, redirects to login page
            res.sendRedirect(req.getContextPath() + LOGIN);
        }
    }

    @Override
    public void destroy() {
        
    }
}
