<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Elevate Home Register</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css"/>
</head>
<body>
  <div class="container">
    <div class="left-side">
      <img src="${pageContext.request.contextPath}/resources/images/system/loginImg.png" alt="Register Illustration"/>
    </div>

    <div class="right-side">
      <div class="register-box">
        <div class="title">ELEVATE HOME</div>
        <h2>SIGN UP</h2>

        <c:if test="${not empty error}">
          <p style="color: red;">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
          <div class="input-row">
            <input type="text" name="firstName" placeholder="First Name" value="${firstName}" required />
            <input type="text" name="lastName" placeholder="Last Name" value="${lastName}" required />
          </div>
<!-- newly added PN and address -->
          <input type="email" name="email" placeholder="Email" value="${email}" required />
          <input type="text" name="phoneNumber" placeholder="Phone Number" value="${phoneNumber}" required />
          <input type="text" name="address" placeholder="Address" value="${address}" required />

          <div class="role-options">
            <label>Role</label><br/>
            <label><input type="radio" name="role" value="admin" ${role == 'admin' ? 'checked' : ''}/> Admin</label>
            <label><input type="radio" name="role" value="user" ${role == 'user' ? 'checked' : ''}/> User</label>
          </div>

          <input type="password" name="password" placeholder="Enter Password" required />
          <input type="password" name="confirmPassword" placeholder="Confirm Password" required />
		<!-- new -->
		<!-- Add this part here -->
    <label for="profileImage">Upload Profile Image:</label>
    <input type="file" name="profileImage" id="profileImage" accept="image/*" >
          <p class="login-link">
            Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a>
          </p>

          <button type="submit" class="register-btn">REGISTER</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
