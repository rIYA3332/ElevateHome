<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.elevateHome.model.CategoryModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Database | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productDatabase.css"/> <!-- Use same CSS as product -->
</head>
<body>

<!-- NAVIGATION (identical to product.jsp) -->
<header class="header">
  <nav class="nav">
    <div class="left-links">
      <a href="${pageContext.request.contextPath}/adminDashboard">Admin Dashboard</a>
      <a href="${pageContext.request.contextPath}/user">User</a>
      <a href="${pageContext.request.contextPath}/product">Product</a>
      <a href="${pageContext.request.contextPath}/category">Category</a>
    </div>
    <div class="logo">ELEVATE HOME</div>
    <div class="right-links">
      <form action="${pageContext.request.contextPath}/logOut" method="post" style="display:inline;">
        <button type="submit" class="logout-btn">Log Out</button>
      </form>
      <div class="profile">
        <img src="${pageContext.request.contextPath}/resources/images/system/profile-admin.png" alt="Profile Picture">
        <div class="profile-info">
          <p>Admin</p>
          <small>Admin123@gmail.com</small>
        </div>
      </div>
    </div>
  </nav>
</header>

<!-- SEARCH BAR & CONTROLS (same as product.jsp) -->
<div class="user-database-container">
  <form method="GET" action="category">
    <input type="text" 
           class="search-bar" 
           name="search" 
           placeholder="Search for category information"
           value="${param.search}">
    <div class="controls">
      <button type="submit" class="search-btn">Search <img src="${pageContext.request.contextPath}/resources/images/system/search.png" alt="Search Icon"></button>
      <button class="filter-btn">Filter <img src="${pageContext.request.contextPath}/resources/images/system/filter search.png" alt="Filter Icon"></button>
    </div>
  </form>
</div>

<!-- CATEGORY DATABASE TABLE -->
<div class="user-database">
  <h2>Category Database</h2>
  <table>
    <thead>
      <tr>
        <th>Category ID</th>
        <th>Name</th>
        <th>Product List</th>
        <th>Total Items</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <% 
      String editId = (String) request.getAttribute("editId");
      List<CategoryModel> categories = (List<CategoryModel>) request.getAttribute("categoryList");
      String searchTerm = (String) request.getAttribute("searchTerm");
      
      if (categories != null) {
          for (CategoryModel category : categories) {
              boolean isEditing = String.valueOf(category.getCategory_id()).equals(editId);
              boolean isMatch = searchTerm != null 
                              && !searchTerm.isEmpty() 
                              && category.getCategory_name().toLowerCase().contains(searchTerm.toLowerCase());
      %>
      <tr class="<%= isMatch ? "highlighted" : "" %>">
        <form action="category" method="post">
          <td><input type="text" name="category_id" value="<%= category.getCategory_id() %>" readonly></td>
          <td><input type="text" name="category_name" value="<%= category.getCategory_name() %>" <%= isEditing ? "" : "readonly" %>></td>
          <td><input type="text" name="product_list" value="<%= category.getProduct_list() %>" <%= isEditing ? "" : "readonly" %>></td>
          <td><input type="text" name="total_product_items" value="<%= category.getTotal_product_items() %>" <%= isEditing ? "" : "readonly" %>></td>
          <td>
            <% if (isEditing) { %>
              <button type="submit" name="action" value="confirmUpdate">Save</button>
              <button type="submit" name="action" value="cancelEdit">Cancel</button>
            <% } else { %>
              <button type="submit" name="action" value="update">Update</button>
              <button type="submit" name="action" value="delete">Delete</button>
            <% } %>
          </td>
        </form>
      </tr>
      <% }} %>
      <!-- New Category Row -->
      <tr>
        <form action="category" method="post">
          <td>Auto</td>
          <td><input type="text" name="category_name" required></td>
          <td><input type="text" name="product_list"></td>
          <td><input type="number" name="total_product_items"></td>
          <td><button type="submit" name="action" value="create">Add</button></td>
        </form>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>