<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Elevate Home</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css"/>
</head>
<body>
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
        <!-- log out button -->
        <form action="${pageContext.request.contextPath}/logOut" method="post" style="display:inline;">
  <button type="submit" class="logout-btn">Log Out</button>
</form>

<c:if test="${not empty sessionScope.email}">
  <p>Welcome, ${sessionScope.email}</p>
</c:if>


      </div>
    </nav>
  </header>

  <section class="hero">
  <div class="hero-left-image">
    <img src="resources/images/system/greenLamp.png" alt="Left Lamp">
  </div>
  <div class="hero-text">
    <h2>Small Details, <br>Big Home Impact</h2>
    <p>Elevate your space with thoughtfully chosen pieces that bring warmth, style, and personality to every corner.</p>
    <button>Shop Now</button>
  </div>
  <img src="resources/images/system/greenTable.png" alt="Hero Image">
</section>

  <main class="main-content">
    <h3 class="section-title">NEW ARRIVALS</h3>
    <div class="products-grid">
      <div class="product">
      <div class="product-image-box">
        <img src="resources/images/system/tableLamp.png" alt="TABLE LAMP">
        </div>
        <p class="product-name">TABLE LAMP</p>
        <p class="product-price">NRS 999</p>
      </div>
      <div class="product">
      <div class="product-image-box">
        <img src="resources/images/system/table.png" alt="TABLE">
        </div>
        <p class="product-name">TABLE</p>
        <p class="product-price">NRS 1999</p>
      </div>
      <div class="product">
      <div class="product-image-box">
        <img src="resources/images/system/wallMirror.png" alt="WALL MIRROR">
        </div>
        <p class="product-name">WALL MIRROR</p>
        <p class="product-price">NRS 3199</p>
      </div>
      <div class="product">
      <div class="product-image-box">
        <img src="resources/images/system/clock.png" alt="CLOCK">
        </div>
        <p class="product-name">CLOCK</p>
        <p class="product-price">NRS 799</p>
      </div>
      <div class="product">
      <div class="product-image-box">
        <img src="resources/images/system/flowerStand.png" alt="FLOWER STAND">
        </div>
        <p class="product-name">FLOWER STAND</p>
        <p class="product-price">NRS 1199</p>
      </div>
    </div>

    <h3 class="section-title">SALES</h3>
    <div class="sales-layout">
      <div class="product large sales-left">
      <div class="product-image-box-white">
        <img src="resources/images/system/whiteTableLamp.png" alt="WHITE TABLE LAMP">
        </div>
       
        <p class="product-name">WHITE TABLE LAMP</p>
        <p class="product-price">NRS 3199</p>
     
      </div>
      <div class="sales-right">
        <div class="product">
        <div class="product-image-box">
          <img src="resources/images/system/whiteRoundSofa.png" alt="WHITE ROUND SOFA">
          </div>
          
          <p class="product-name">WHITE ROUND SOFA</p>
          <p class="product-price">NRS 57099</p>
          
        </div>
        <div class="product">
        <div class="product-image-box">
          <img src="resources/images/system/plantVases.png" alt="PLANT VASES">
          </div>
          
          <p class="product-name">PLANT VASES</p>
          <p class="product-price">NRS 999</p>
        
        </div>
        <div class="product">
        <div class="product-image-box">
          <img src="resources/images/system/greenRoundSOfa.png" alt="GREEN ROUND SOFA">
          </div>
        
          <p class="product-name">GREEN ROUND SOFA</p>
          <p class="product-price">NRS 57099</p>
          
        </div>
        <div class="product">
        <div class="product-image-box">
          <img src="resources/images/system/chair.png" alt="CHAIR">
          </div>
         
          <p class="product-name">CHAIR</p>
          <p class="product-price">NRS 2199</p>
         
        </div>
      </div>
    </div>
  </main>

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
