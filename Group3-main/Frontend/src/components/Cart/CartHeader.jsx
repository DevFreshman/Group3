// src/components/Cart/CartHeader.jsx
import React from "react";
import "../../assets/Styles/Cart/CartHeader.css";

const CartHeader = () => {
  return (
    <div className="cart-header">
      <div className="col header-checkbox">Product</div>
      <div className="col header-price">Price</div>
      <div className="col header-quantity">Quantity</div>
      <div className="col header-total">Total</div>
      <div className="col header-action">Action</div>
    </div>
  );
};

export default CartHeader;
