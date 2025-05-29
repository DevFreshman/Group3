// src/components/Cart/EmptyCart.jsx
import React from "react";
import { useNavigate } from "react-router-dom";
import emptyCartImg from "../../assets/GirlCute.png"; 

import "../../assets/Styles/Cart/EmptyCart.css";

const EmptyCart = () => {
  const navigate = useNavigate();

  return (
    <div className="empty-cart">
      <img src={emptyCartImg} alt="Giỏ hàng trống" />
      <p>Your cart is empty</p>
      <button onClick={() => navigate("/product")}>Buy now</button>
    </div>
  );
};

export default EmptyCart;
