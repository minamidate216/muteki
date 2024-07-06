<template>
    <footer class="footer">
      <div class="footer-container">
        <div class="footer-logo">
          <img src="@/assets/mainlogo2.png" alt="G's Work Logo" class="logo">
          <p style="margin-left: 20px;">キャッチコピー</p>
          <div class="social-icons">
            <a href="#"><i class="fab fa-instagram"></i></a>
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-whatsapp"></i></a>
            <a href="#"><i class="fab fa-linkedin-in"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
          </div>
        </div>
        <div class="footer-links">
          <h3>HOMEPAGE</h3>
          <ul>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
          </ul>
        </div>
        <div class="footer-links">
          <h3>HOMEPAGE</h3>
          <ul>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
            <li><a href="#">Lorem Ipsum</a></li>
          </ul>
        </div>
      </div>
    </footer>
  </template>
  
  <script setup lang="ts">
  // You can import any needed resources or setup here
  </script>
  <style scoped>
  .footer {
    background: linear-gradient(to right, #041143 0%, #5E7B89 100%);
    color: white;
    padding: 40px 0;
  }
  
  .footer-container {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    max-width: none;
    width: 80%;
    padding: 0 20px;
  }
  
  .footer-logo {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  
  .footer-logo .logo {
    width: 150px;
    margin-bottom: 10px;
    margin-left: 20px;
  }
  
  .footer-logo p {
    margin-bottom: 20px;
  }
  
  .social-icons {
    display: flex;
    gap: 20px;
  }
  
  .social-icons a {
    color: white;
    font-size: 24px;
  }
  
  .footer-links {
    display: flex;
    flex-direction: column;
  }
  
  .footer-links h3 {
    font-size: 20px;
    margin-bottom: 15px;
  }
  
  .footer-links ul {
    list-style: none;
    padding: 0;
  }
  
  .footer-links ul li {
    margin-bottom: 8px;
  }
  
  .footer-links ul li a {
    color: white;
    text-decoration: none;
    font-size: 16px;
  }
  
  .footer-links ul li a:hover {
    text-decoration: underline;
  }
  </style>
  