<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact | Elevate Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contact.css"/>
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

    <!-- CONTACT HEADER SECTION -->
    <section class="contact-header">
  <div class="contact-header-content">
    <div class="contact-header-text">
      <h1>Contact Us</h1>
      <p>Whether you have a question, feedback, or just want to say hello, we’re here to help. Fill out the form or reach out to us through the details below—we’ll get back to you as soon as possible!</p>
    </div>
    <img src="resources/images/system/sideLamp.png" alt="Lamp" class="lamp-image">
  </div>
</section>


    <!-- CONTACT DETAILS & FORM SECTION -->
    <section class="contact-section">
        <div class="contact-left">
            <div class="contact-info-box">
                <img src="resources/images/system/office-building.png" alt="Address Icon">
                <div>
                    <h4>COMPANY ADDRESS</h4>
                    <p>Kathmandu, Nepal</p>
                </div>
            </div>
            <div class="contact-info-box">
                <img src="resources/images/system/contact-mail.png" alt="Call Icon">
                <div>
                    <h4>CONTACT US</h4>
                    <p>987654321 / 0177777</p>
                </div>
            </div>
            <div class="contact-info-box">
                <img src="resources/images/system/mail.png" alt="Email Icon">
                <div>
                    <h4>EMAIL</h4>
                    <p>elevatehome@gmail.com</p>
                </div>
            </div>
            <div class="contact-info-box">
                <img src="resources/images/system/activeHours.png" alt="Clock Icon">
                <div>
                    <h4>ACTIVE HOURS</h4>
                    <p>Mon–Sat (9:00–9:00)</p>
                </div>
            </div>
            <hr>
            <h4>FOLLOW US:</h4>
            <div class="social-icons">
                <img src="resources/images/system/facebook.png" alt="Facebook">
        		<img src="resources/images/system/youtube.png" alt="YouTube">
        		<img src="resources/images/system/twitter.png" alt="Twitter">
        		<img src="resources/images/system/pinterest.png" alt="Pinterest">
            </div>
        </div>

        <div class="contact-right">
            <h3>GET IN TOUCH</h3>
            <p>Have a question or just want to chat? We’re always happy to hear from you. Drop us a message and we’ll get back to you soon!</p>
            <form>
                <div class="input-row">
                    <input type="text" placeholder="Your Full Name" required>
                    <input type="email" placeholder="Your Email" required>
                </div>
                <input type="text" placeholder="Subject" required>
                <textarea placeholder="Message..." required></textarea>
                <button type="submit">
                    <img src="resources/images/system/mail.png" alt="Send"> Send Message
                </button>
            </form>
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
