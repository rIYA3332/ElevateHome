<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userProfile.css?v=2">
</head>
<body>
<!-- NAVIGATION -->
    <header class="header">
    <nav class="nav">
      <div class="left-links">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/userProfile">User Profile</a>
        <a href="${pageContext.request.contextPath}/aboutUs">About Us</a>
        <a href="${pageContext.request.contextPath}/contact">Contact</a>

      </div>		
			<div class="logo">ELEVATE HOME</div>
      <div class="right-links">
        <span class="search-text">Search</span>
        <span class="search-icon"><img src="${pageContext.request.contextPath}/resources/images/system/search.png" alt="Search Icon"></span>
        <a href="#">Log Out</a>
      </div>
    </nav>
  </header>
  <!-- new -->
  
</div>
 <!-- PROFILE BOX -->
<section class="profile-section">
  <div class="profile-container">
    <div class="profile-left">
      <!-- Dynamic profile image with fallback -->
      <<img src="${pageContext.request.contextPath}/resources/images/system/user_images/${sessionScope.profileImage}" 
           class="profile-image" 
           onerror="this.src='${pageContext.request.contextPath}/resources/images/system/loginUser.png'">
      <h2 class="profile-name">${requestScope.firstName} ${requestScope.lastName}</h2>
    </div>
  </div>
  
  <div class="profile-buttons">
    <button class="update-photo">Update New Photo</button>
    <button class="delete-photo">Delete Current Picture</button>
  </div>
</section>




<!-- INFORMATION BOX -->
    <section class="info-section">
        <h2>My Information</h2>
        <div class="info-box">
            <label>Name</label>
            <input type="text" value="${requestScope.firstName} ${requestScope.lastName}" readonly>

            <label>Email</label>
            <input type="text" value="${requestScope.email}" readonly>

            <label>Address</label>
            <input type="text" value="${requestScope.address}" readonly>

            <label>Phone Number</label>
            <input type="text" value="${requestScope.phoneNumber}" readonly>
            
            <label>Password</label>
            <input type="text" value="${requestScope.password}" readonly>
        </div>
    </section>

<!-- CHANGE PHONE NUMBER BOX -->

<form action="${pageContext.request.contextPath}/UpdateProfileController" method="post" enctype="multipart/form-data" >
    <section class="edit-section">
        <h2 class="section-title">Edit My Information</h2>
		
		
    
        <div class="edit-box">
            <h3><img src="${pageContext.request.contextPath}/resources/images/system/phone-call.png" class="icon"> Change Phone Number</h3>
            <div class="input-row">
                <input type="text" name="currentPhoneNumber" value="${requestScope.phoneNumber}" readonly placeholder="Current Phone Number">
                <input type="text" name="newPhoneNumber" required placeholder="New Phone Number">
                <input type="text" name="confirmPhoneNumber" required placeholder="Confirm Phone Number">
            </div>
        </div>

        <div class="edit-box">
            <h3><img src="${pageContext.request.contextPath}/resources/images/system/map.png" class="icon"> Change Address</h3>
            <div class="input-row">
                <input type="text" name="currentAddress" value="${requestScope.address}" readonly placeholder="Current Address">
                <input type="text" name="newAddress" required placeholder="New Address">
                <input type="text" name="confirmAddress" required placeholder="Confirm Address">
            </div>
        </div>
        
        <div class="edit-box">
            <h3><img src="${pageContext.request.contextPath}/resources/images/system/password.png" class="icon"> Change Password</h3>
            <div class="input-row">
                <input type="text" name="currentPassword" value="${requestScope.password}" readonly placeholder="Current Password">
                <input type="text" name="newPassword" required placeholder="New Password">
                <input type="text" name="confirmPassword" required placeholder="Confirm Password">
            </div>
        </div>
<div class="edit-box">
        <h3><img src="${pageContext.request.contextPath}/resources/images/system/update.png" class="icon"> 
            Update Profile Image
        </h3>
        <div class="input-row">
            <input type="file" name="profileImage" accept="image/*">
        </div>
    </div>
        <button type="submit" class="proceed-button">Update Profile</button>
    </section>
</form>




  <!-- FOOTER -->
   <footer class="footer">
    <div class="footer-top">
      <div class="footer-about">
        <h4>ELEVATE HOME</h4>
        <p>Elevate your space with thoughtfully chosen pieces that bring warmth, style, and personality to every corner.</p>
      </div>
      <div class="footer-nav">
        <h4>Navigation</h4>
        <ul>
          <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
          <li><a href="${pageContext.request.contextPath}/userProfile">User Profile</a></li>
          <li><a href="${pageContext.request.contextPath}/aboutUs">About Us</a></li>
          <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
        </ul>
      </div>
      <div class="footer-help">
        <h4>Get Help</h4>
        <ul>
          <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
          <li><a href="#">FAQs</a></li>
        </ul>
      </div>
      <div class="footer-contact">
        <h4>More About Us</h4>
        <ul>
          <li><a href="${pageContext.request.contextPath}/aboutUs">About Us</a></li>
          <li><a href="#">Blog</a></li>
        </ul>
      </div>
    </div>

    <div class="footer-info">
  <div class="footer-contact-group">
    <span><img src="resources/images/system/map.png" /> Kathmandu, Nepal</span>
    <span><img src="resources/images/system/phone-call.png" /> 987654321 / 0177777</span>
    <span><img src="resources/images/system/gmail.png" /> elevatehome@gmail.com</span>
  </div>
      <div class="footer-social">
        <img src="resources/images/system/facebook.png" alt="Facebook">
        <img src="resources/images/system/youtube.png" alt="YouTube">
        <img src="resources/images/system/twitter.png" alt="Twitter">
        <img src="resources/images/system/pinterest.png" alt="Pinterest">
      </div>
    </div>

    <div class="footer-copy">
      <p>@2025 Elevate Home - All Rights Reserved</p>
    </div>
  </footer>
</body>
</html>