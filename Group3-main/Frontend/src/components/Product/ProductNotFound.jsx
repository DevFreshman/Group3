import React from "react";
import { useNavigate } from "react-router-dom";
import "../../assets/Styles/Product/ProductNotFound.css";

const ProductNotFound = () => {
  const navigate = useNavigate();

  const handleGoBack = () => {
    navigate("/");
  };

  return (
    <div className="product-not-found">
      <h1>Product Not Found</h1>
      <p>We couldn't find the product you're looking for. It may have been removed or the ID is incorrect.</p>
      <button onClick={handleGoBack}>Go Back Home </button>
    </div>
  );
};

export default ProductNotFound;
