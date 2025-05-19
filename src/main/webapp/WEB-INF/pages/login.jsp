<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Elevate Home Login</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
  <div class="container">
    <!-- Left image panel -->
    <div class="left-side">
      <img src="${pageContext.request.contextPath}/resources/images/system/loginImg.png" alt="Login Illustration" />
    </div>

    <!-- Right form panel -->
    <div class="right-side">
      <div class="title">ELEVATE HOME</div>
      <!--  -->
      <c:if test="${not empty message}">
      <div class="error-message">${error}</div>
  <div class="floating-alert">${message}</div>
  <c:remove var="message" scope="session" />
</c:if>



      <form class="login-box" action="login" method="post">
        <h2>Login Account</h2>

        <div class="input-row">
          <img src="${pageContext.request.contextPath}/resources/images/system/loginUser.png" class="icon" alt="User Icon">
          <input type="text" name="email" placeholder="Email" required />
        </div>

        <div class="input-row">
          <img src="${pageContext.request.contextPath}/resources/images/system/padlock.png" class="icon" alt="Lock Icon">
          <input type="password" name="password" placeholder="Password" required />
        </div>

          

        <div class="account-links">
          <span>Create New Account?</span>
          <a href="${pageContext.request.contextPath}/register">Register</a>
        </div>

        <button class="login-btn" type="submit">LOGIN</button>
      </form>
    </div>
  </div>
</body>
</html>
