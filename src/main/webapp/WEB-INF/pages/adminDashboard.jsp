<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminDashboard.css"/>
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
      
      <!-- log out button -->
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

<!-- Main Dashboard -->
<!-- Inside <body> -->
<div class="dashboard-wrapper">

    <!-- TOP DASHBOARD CARDS CONTAINER -->
    <div class="top-dashboard-container">
        <h1>Dashboard</h1>
        <div class="stats-row">
            <div class="stat-card purple">
    <div class="stat-info">
        <div class="stat-text">
            <p class="stat-title">Total User</p>
            <p class="stat-value">${totalUsers}</p>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/system/total-user.png" class="stat-icon" />
    </div>
</div>

<div class="stat-card blue">
    <div class="stat-info">
        <div class="stat-text">
            <p class="stat-title">Total Product</p>
            <p class="stat-value">${totalProducts}</p>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/system/product.png" class="stat-icon" />
    </div>
</div>
<div class="stat-card peach">
    <div class="stat-info">
        <div class="stat-text">
            <p class="stat-title">Low Stock</p>
            <p class="stat-value">${lowStockCount}</p>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/system/low.png" class="stat-icon" />
    </div>
</div>
<div class="stat-card green">
    <div class="stat-info">
        <div class="stat-text">
            <p class="stat-title">Revenue</p>
            <p class="stat-value">${totalRevenue}</p>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/system/money-tag.png" class="stat-icon" />
    </div>
</div>
        </div>
    </div>

    <!-- BOTTOM TABLES -->
    <!-- BOTTOM TABLES -->
<div class="tables-row">
    <div class="table-card wide-table">
        <h2>Most Selling Products</h2>
        <table>
            <thead>
                <tr><th>Name</th><th>Sales</th></tr>
            </thead>
            <tbody>
                <%
    String[][] topProducts = (String[][]) request.getAttribute("topProducts");
    if (topProducts != null) {
        for (String[] product : topProducts) {
            if (product != null && product[0] != null && !product[0].isEmpty()) {
%>
                <tr>
                    <td><%= product[0] %></td>
                    <td><%= product[1] %></td>
                </tr>
<%
            }
        }
    }
%>
            </tbody>
        </table>
    </div>
    <div class="table-card narrow-table">
        <h2>Low Stock Alert</h2>
        <table>
            <thead><tr><th>Product</th></tr></thead>
            <tbody>
                <%
    String[] lowStockProducts = (String[]) request.getAttribute("lowStockProducts");
    if (lowStockProducts != null) {
        for (String product : lowStockProducts) {
            if (product != null && !product.isEmpty()) {
%>
                <tr><td><%= product %></td></tr>
<%
            }
        }
    }
%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
