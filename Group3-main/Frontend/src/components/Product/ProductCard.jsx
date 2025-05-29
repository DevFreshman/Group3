import { useNavigate } from "react-router-dom";
import "../../assets/Styles/Product/ProductCard.css";

const ProductCard = ({ product }) => {
  const navigate = useNavigate();
  const handleViewProduct = (e) => {
    e.stopPropagation();
    navigate(`/product/${product.productId}`);
  };

  return (
    <div className="product-card" onClick={handleViewProduct}>
      <img src={product.image_url} alt={product.name} />
      <h3>{product.name}</h3>
      <p className="price">
        Price:{" "}
        <strong>
          {product.price.toLocaleString("en-US", {
            style: "currency",
            currency: "USD",
          })}
        </strong>
      </p>
      <p>Quantity: {product.quantity}</p>

      <div className="button-group">
        <button className="view-button" onClick={handleViewProduct}>
          Buy Now
        </button>
      </div>
    </div>
  );
};

export default ProductCard;
