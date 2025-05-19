<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.elevateHome.model.ProductModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Database | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productDatabase.css"/>
</head>
<body>

<!-- NAVIGATION -->
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

<!-- SEARCH BAR & CONTROLS -->
<div class="user-database-container">
  <form method="GET" action="product">
    <input type="text" 
           class="search-bar" 
           name="search" 
           placeholder="Search for product information"
           value="${param.search}">
    <div class="controls">
      <button type="submit" class="search-btn">Search <img src="${pageContext.request.contextPath}/resources/images/system/search.png" alt="Search Icon"></button>
      <button class="filter-btn">Filter <img src="${pageContext.request.contextPath}/resources/images/system/filter search.png" alt="Filter Icon"></button>
    </div>
  </form>
</div>

<!-- PRODUCT DATABASE -->
<div class="user-database">
  <h2>Product Database</h2>
  <table>
    <thead>
      <tr>
        <th>Product ID</th>
        <th>Name</th>
        <th>Brand</th>
        <th>Price</th>
        <th>Sales</th>
        <th>Stock</th>
        <th>Status</th>
        <th>Image</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
     <%
  String editId = (String) request.getAttribute("editId");
  List<ProductModel> productList = (List<ProductModel>) request.getAttribute("productList");
  String searchTerm = (String) request.getAttribute("searchTerm");
  
  if (productList != null) {
    for (ProductModel product : productList) {
      String currentId = String.valueOf(product.getProduct_id());
      boolean isEditing = currentId.equals(editId);
      boolean isMatch = searchTerm != null 
                        && !searchTerm.isEmpty() 
                        && product.getProduct_name().toLowerCase().contains(searchTerm.toLowerCase());
%>
<tr class="<%= isMatch ? "highlighted" : "" %>"> 
  <form action="product" method="post" enctype="multipart/form-data">
    <input type="hidden" name="existing_product_image" value="<%= product.getProduct_image() != null ? product.getProduct_image() : "" %>" />
    <td><input type="text" name="product_id" value="<%= currentId %>" readonly /></td>
    <td><input type="text" name="product_name" value="<%= product.getProduct_name() %>" <%= isEditing ? "" : "readonly" %> /></td>
    <td><input type="text" name="product_brand" value="<%= product.getProduct_brand() %>" <%= isEditing ? "" : "readonly" %> /></td>
    <td><input type="text" name="product_price" value="<%= product.getProduct_price() %>" <%= isEditing ? "" : "readonly" %> /></td>
    <td><input type="text" name="product_sales" value="<%= product.getProduct_sales() %>" <%= isEditing ? "" : "readonly" %> /></td>
    <td><input type="text" name="product_stock" value="<%= product.getProduct_stock() %>" <%= isEditing ? "" : "readonly" %> /></td>
    <td><input type="text" name="product_status" value="<%= product.getProduct_status() %>" <%= isEditing ? "" : "readonly" %> /></td>
    
    <!-- Image Cell - Corrected Section -->
    <td>
      <% if (isEditing) { %>
        <input type="file" name="product_image" />
      <% } else { 
           if (product.getProduct_image() != null && !product.getProduct_image().isEmpty()) { %>
            <img src="${pageContext.request.contextPath}/resources/images/system/product_images/<%= product.getProduct_image() %>" 
                 width="50" 
                 alt="Product Image"/>
      <%   } else { %>
            <span class="no-image">No Image</span>
      <%   }
         } %>
    </td>
    
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
<%
    }
  }
%>

      <!-- New Product Row -->
      <tr>
        <form action="product" method="post" enctype="multipart/form-data">
          <td>Auto</td>
          <td><input type="text" name="product_name" required /></td>
          <td><input type="text" name="product_brand" required /></td>
          <td><input type="text" name="product_price" required /></td>
          <td><input type="text" name="product_sales" required /></td>
          <td><input type="text" name="product_stock" required /></td>
          <td><input type="text" name="product_status" required /></td>
          <td><input type="file" name="product_image" required /></td>
          <td><button type="submit" name="action" value="create">Add</button></td>
        </form>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>