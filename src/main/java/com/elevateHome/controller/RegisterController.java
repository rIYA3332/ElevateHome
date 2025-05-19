package com.elevateHome.controller;

import jakarta.servlet.ServletException;


import com.elevateHome.util.ImageUtil;// new
import com.elevateHome.util.PasswordUtil;
import com.elevateHome.util.ValidationUtil;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.elevateHome.model.UserModel;
import com.elevateHome.service.register;

/**
 * @author Riya Bhatta
 * 
 *         The RegisterController handles the user registration process. It
 *         validates input fields, encrypts the password, constructs a UserModel
 *         object, and inserts it into the database.
 * 
 *         - ValidationUtil: Used to validate each input (e.g., name, email,
 *         role, password). - PasswordUtil.encrypt(): Encrypts the password
 *         using AES encryption based on email as the salt/key. -
 *         register.insert(): Inserts the validated and encrypted user into the
 *         database. - request.getParameter(): Extracts form field values from
 *         the submitted registration form. - request.setAttribute(): Passes
 *         error and input values back to the JSP page in case of validation
 *         failure. - request.getRequestDispatcher().forward(): Forwards the
 *         user to the registration form if errors are found. -
 *         response.sendRedirect(): Redirects to login page if registration is
 *         successful, or to error page if failed.
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Forwards the user to the registration page (register.jsp) when accessed via
	 * GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * Handles the POST request for user registration.
	 * 
	 * The process includes: - Extracting and validating form fields - Encrypting
	 * the password - Creating a UserModel object - Inserting the user into the
	 * database - Redirecting or forwarding based on success/failure
	 * 
	 * @param request  the HTTP request containing registration form inputs
	 * @param response the HTTP response used for forwarding or redirection
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// new
		Part imagePart = request.getPart("profileImage");
		String profileImage = null;
		if (imagePart != null && imagePart.getSize() > 0) {
			String uploadDir = "C:\\Users\\Dell\\eclipse-workspace\\ElevateHome\\src\\main\\webapp\\resources\\images\\system/user_images";
			profileImage = ImageUtil.saveImage(imagePart, uploadDir);
		}

		//
		String errorMessage = null;

		// Validation
		if (ValidationUtil.isNullOrEmpty(firstName)) {
			errorMessage = "First name is required.";
		} else if (!ValidationUtil.isAlphabetic(firstName)) {
			errorMessage = "First name must contain only letters.";
		} else if (ValidationUtil.isNullOrEmpty(lastName)) {
			errorMessage = "Last name is required.";
		} else if (!ValidationUtil.isAlphabetic(lastName)) {
			errorMessage = "Last name must contain only letters.";
		} else if (ValidationUtil.isNullOrEmpty(email)) {
			errorMessage = "Email is required.";
		} else if (!ValidationUtil.isValidEmail(email)) {
			errorMessage = "Invalid email format.";
		} else if (ValidationUtil.isNullOrEmpty(role)) {
			errorMessage = "Role is required.";
		} else if (!ValidationUtil.isValidRole(role)) {
			errorMessage = "Role must be 'admin' or 'user'.";
		} else if (ValidationUtil.isNullOrEmpty(phoneNumber)) {
			errorMessage = "Phone number is required.";
		} else if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
			errorMessage = "Phone number must be 10 digits.";
		} else if (ValidationUtil.isNullOrEmpty(password)) {
			errorMessage = "Password is required.";
		} else if (!ValidationUtil.isValidPassword(password)) {
			errorMessage = "Password must be at least 8 characters, with 1 uppercase letter, 1 number, and 1 special character.";
		} else if (!ValidationUtil.doPasswordsMatch(password, confirmPassword)) {
			errorMessage = "Passwords do not match.";
		}

		// Stops here if any of the above failed
		if (errorMessage != null) {
			request.setAttribute("error", errorMessage);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("address", address);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

		// Additional checks: email and phone number existence
		register regService = new register();
		try {
			if (regService.emailExists(email)) {
				errorMessage = "This email is already taken. Please use another.";
			} else if (regService.phoneNumberExists(phoneNumber)) {
				errorMessage = "This phone number is already used by another account. Please use a different phone number.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Something went wrong while checking existing records.";
		}

		if (errorMessage != null) {
			request.setAttribute("error", errorMessage);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("address", address);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

		// Encrypting the password
		String encryptedPassword = PasswordUtil.encrypt(email, password);

		// If encryption fails, forwards back with error
		if (encryptedPassword == null) {
			request.setAttribute("error", "Password encryption failed. Please try again.");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("address", address);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

		// UserModel with the encrypted password
		UserModel user = new UserModel(0, firstName, lastName, email, role, encryptedPassword, phoneNumber, address,
				profileImage);
		boolean inserted = false;

		// Inserting the user into the database
		try {
			inserted = new register().insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Redirects based on insertion result
		if (inserted) {
			request.getSession().setAttribute("message", "Registration successful! Please login.");
			response.sendRedirect("login");
		} else {
			response.sendRedirect("error.jsp");
		}
	}
}
