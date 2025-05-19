<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.elevateHome.model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Database | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userDatabase.css"/>
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
  <input type="text" class="search-bar" placeholder="Search for user information">
  <div class="controls">
    <button class="sort-btn">Search <img src="${pageContext.request.contextPath}/resources/images/system/search.png" alt="Sort Icon"></button>
    <button class="filter-btn">Filter <img src="${pageContext.request.contextPath}/resources/images/system/filter search.png" alt="Filter Icon"></button>
  </div>
</div>

<!-- USER DATABASE TABLE -->
<div class="user-database">
  <h2>User Database</h2>
  <div class="tabs">
    <span class="active">All</span>
    <span>Premium</span>
    <span>Normal</span>
  </div>
  <table>
    <thead>
      <tr>
        <th>User ID</th>
        <th>User Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Address</th>
        <th>Phone Number</th>
      </tr>
    </thead>
    <tbody>
<%
    List<UserModel> userList = (List<UserModel>) request.getAttribute("userList");
    if (userList != null) {
        for (UserModel user : userList) {
%>
    <tr>
        <td><%= user.getUserId() %></td>
        <td><%= user.getUserFirstName() %> <%= user.getUserLastName() %></td>
        <td><%= user.getUserEmail() %></td>
        <td>********</td>
        <td><%= user.getAddress() %></td>
        <td><%= user.getPhoneNumber() %></td>
    </tr>
<%
        }
    }
%>
</tbody>


  </table>
</div>



</body>
</html>
