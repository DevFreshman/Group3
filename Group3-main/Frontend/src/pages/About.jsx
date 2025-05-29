import React from 'react'
import { useNavigate } from 'react-router-dom';
import '../assets/Styles/About.css'
const About = () => {
  const navigate = useNavigate();

  const handleShopNow = () => {
    navigate('/product');
  };

  return (
    <div className="about-section">
      <div className="about-hero">
        <div className="hero-text">
          <h1>ABOUT US</h1>
          <p>Your trusted seed supplier for healthy and fresh plants</p>
        </div>
      </div>

      <div className="about-content container">
        <div className="about-image">
          <img
            src="https://wallpapercave.com/wp/wp9332541.jpg"
            alt="Seeds and gardening"
          />
        </div>
        <div className="text-block">
          <h2>Welcome to Verdancia Market</h2>
          <p>
            We provide high-quality standard seeds, ensuring the best output for your garden or farm.
            Our seeds are carefully selected and tested to guarantee quality and germination.
          </p>
          <p>
            We also offer reliable seed delivery services to households, companies, and restaurants.
            Whether you are a hobbyist or a professional, we are here to serve your needs.
          </p>
          <p>
            Please place your order now or leave us an email if you want premium quality seeds delivered fast.
          </p>
          <button className="shop-btn" onClick={handleShopNow}>Shop Now</button>
        </div>
      </div>
    </div>
  );
};

export default About;