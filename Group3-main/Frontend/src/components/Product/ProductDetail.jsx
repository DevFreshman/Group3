import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "../../assets/Styles/Product/ProductDetail.css";
import { useAuth } from "../../context/UseAuthContext";
import { getProductByIdAPI } from "../../api/productAPI";
import { addProductToCart } from "../../api/cartAPI";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ProductDetail = () => {
  const { user } = useAuth();
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [addingToCart, setAddingToCart] = useState(false);
  const [quantityToBuy, setQuantityToBuy] = useState(1);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await getProductByIdAPI(id);
        setProduct(data);
      } catch (error) {
        console.error("Failed to load product:", error);
        toast.error("Failed to load product");
      } finally {
        setLoading(false);
      }
    };
    fetchProduct();
  }, [id]);

  const handleAddToCart = async (productId, quantity) => {
    if (!user) {
      toast.warn("Please sign in before adding to cart");
      navigate("/signin");
      return;
    }

    try {
      setAddingToCart(true);
      await addProductToCart(productId, quantity);
      toast.success("Product added to cart!");
    } catch (error) {
      console.error("Error adding to cart:", error);
      toast.warn("Run out of product");
    } finally {
      setAddingToCart(false);
    }
  };

  const handleBack = () => {
    navigate(-1);
  };

  const formatEnum = (enumStr) => {
    return enumStr
      .toLowerCase()
      .split("_")
      .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
      .join(" ");
  };

  const increaseQuantity = () => {
    if (quantityToBuy < product.quantity) {
      setQuantityToBuy((prev) => prev + 1);
    }
  };

  const decreaseQuantity = () => {
    if (quantityToBuy > 1) {
      setQuantityToBuy((prev) => prev - 1);
    }
  };

  if (loading) return <h2>Loading product data...</h2>;
  if (!product) return <h2>Product not found ❌</h2>;

  return (
    <div className="product-detail">
      <button className="back-button" onClick={handleBack}>
        Back
      </button>
      <img src={product.image_url} alt={product.name} />
      <h1>{product.name}</h1>
      <p>
        <strong>Price:</strong>{" "}
        {product.price.toLocaleString("en-US", {
          style: "currency",
          currency: "USD",
        })}
      </p>
      <p>
        <strong>Stock:</strong> {product.quantity}
      </p>
      <p>
        <strong>Category:</strong> {formatEnum(product.category)}
      </p>
      <p>
        <strong>Type:</strong> {formatEnum(product.type)}
      </p>
      <p>
        <strong>Description:</strong> {product.description}
      </p>

      <div className="quantity-control">
        <p>
          <strong>Quantity: </strong>
        </p>
        <button onClick={decreaseQuantity}>-</button>
        <input
          type="number"
          value={quantityToBuy}
          min="1"
          max={product.quantity}
          onChange={(e) => {
            const val = Math.max(
              1,
              Math.min(product.quantity, parseInt(e.target.value) || 1)
            );
            setQuantityToBuy(val);
          }}
          style={{ width: "60px", textAlign: "center", margin: "0 10px" }}
        />
        <button onClick={increaseQuantity}>+</button>
      </div>

      <button
        className="add-to-cart-detail"
        onClick={() => handleAddToCart(product.productId, quantityToBuy)}
        disabled={addingToCart}
      >
        {addingToCart ? "Adding..." : "Add to Cart"}
      </button>
    </div>
  );
};

export default ProductDetail;
