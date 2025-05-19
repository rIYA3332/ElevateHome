<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutUs.css"/>
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
  
<!-- ABOUT HERO SECTION -->
<section class="about-hero">
  <div class="about-left">
    <h1>About Us</h1>
  </div>

  <div class="wallpaper-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/system/colorfullWallpaper.png" alt="Wall Art" class="wall-art-img" />
    <img src="${pageContext.request.contextPath}/resources/images/system/catWallpaper.png" alt="Cat Poster" class="cat-art" />
  </div>

  <img src="${pageContext.request.contextPath}/resources/images/system/greenSofa.png" class="sofa" alt="Green Sofa" />
  <img src="${pageContext.request.contextPath}/resources/images/system/sideLamp.png" class="lamp" alt="Lamp" />
</section>




<!-- MAIN CONTENT SECTION -->
<section class="about-main">
  <!-- Row 1 -->
  <div class="about-row">
    <div class="about-text">
      <h2>Why we are <br>best in the town</h2>
      <p>It is your one stop destination for stylish, elegant, and affordable home decor products. We are passionate about helping you turn your house into a home with designs that reflect your unique personality and lifestyle.</p>
    </div>
    <div class="about-box">
      <h3>Our Mission</h3>
      <p>Our mission is simple: to make interior styling easy and accessible for everyone. Whether you're redesigning a single room or decorating an entire home, we provide the tools, products, and inspiration you need to create beautiful living spaces.</p>
    </div>
  </div>

  <!-- Row 2 -->
  <div class="about-row reverse">
    <div class="about-text">
      <h2>Why choose us?</h2>
      <p>What sets us apart is our commitment to quality, variety, and a smooth user experience. We blend design trends with functionality, ensuring our customers always have access to fresh, stylish, and practical home decor solutions.</p>
    </div>
    <div class="about-box">
      <h3>What we Offer</h3>
      <p>From modern furniture and cozy lighting to chic wall art and timeless accessories, our platform brings a wide range of curated home decor items for all. With user-friendly features, smart inventory management, we make shopping seamless for customers and efficient for administrators.</p>
    </div>
  </div>
</section>


    

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
