// src/components/Footer.js
import "../assets/Styles/Footer.css";
import { Link } from "react-router-dom";
import logo from "../assets/Logo.ico";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="logo">
          <img src={logo} alt="Logo" className="image" />
          <h2>Verdancia Market</h2>
          <p>Your trusted organic product partner.</p>
        </div>

        <div className="links">
          <h4>Quick Links</h4>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/product">Products</Link></li>
            <li><Link to="/about">About Us</Link></li>
            <li><Link to="/contact">Contact</Link></li>
          </ul>
        </div>

        <div className="contact">
          <h4>Contact Us</h4>
          <p>Email: support@verdancia.com</p>
          <p>Phone: +84 916 102 968</p>
        </div>
      </div>

      <div className="bottom">
        <p>© 2025 Verdancia Market. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
