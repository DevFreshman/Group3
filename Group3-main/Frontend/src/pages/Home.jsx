import React from "react";
import { useNavigate } from "react-router-dom";
import products from "../assets/data/products";
import "../assets/Styles/Home.css";

const Home = () => {
  const navigate = useNavigate();

  const handleShopNow = () => {
    navigate("/product");
  };

  const handleBuyNow = (product) => {
    navigate(`/product/${product.id}`);
  };

  return (
    <div className="home-container">
      {/* Big Banner */}
      <section className="big-banner">
        <h1>
          Fresh & Healthy
          <br />
          Seed Products
        </h1>
        <p>Free shipping on all your orders.</p>
        <button className="buy-button" onClick={handleShopNow}>
          Shop Now →
        </button>
      </section>

      {/* Small Banners */}
      <section className="small-banner">
        <div className="banner light">
          <h4>SUMMER SALE</h4>
          <h2>75% OFF</h2>
          <p>Only on Flower Seeds</p>
          <button onClick={handleShopNow}>Shop Now →</button>
        </div>
        <div className="banner dark">
          <h5>BEST DEAL</h5>
          <h3>
            Special Products
            <br />
            Deal of the Month
          </h3>
          <button onClick={handleShopNow}>Shop Now →</button>
        </div>
      </section>

      {/* Features */}
      <section className="features">
        <div className="feature">
          <span>🚚</span>
          <h4>Free Shipping</h4>
          <p>On all orders</p>
        </div>
        <div className="feature">
          <span>🎧</span>
          <h4>24/7 Support</h4>
          <p>Always here for you</p>
        </div>
        <div className="feature">
          <span>🔒</span>
          <h4>Secure Payment</h4>
          <p>100% Protected</p>
        </div>
        <div className="feature">
          <span>📦</span>
          <h4>Money-Back</h4>
          <p>30 Days Guarantee</p>
        </div>
      </section>

      {/* Popular Products */}
      <section className="popular">
        <div className="popular-header">
          <h2>Popular Products</h2>
          <a href="/product">View All →</a>
        </div>
        <div className="popular-grid">
          {products.slice(0, 8).map((product) => (
            <div className="popular-card" key={product.id}>
              <img
                src={product.imageUrl}
                alt={product.name}
                className="product-image"
              />
              <div className="popular-detail">
                <h4>{product.name}</h4>
                <p className="popular-price">
                  {product.price.toLocaleString("en-US", {
                    style: "currency",
                    currency: "USD",
                  })}
                </p>
              </div>
              <button
                className="buy-now-button"
                onClick={() => handleBuyNow(product)}
              >
                Buy Now
              </button>
            </div>
          ))}
        </div>
      </section>

      {/* Sale */}
      <section className="sale">
        <div className="sale-content">
          <h3>SUMMER SALE</h3>
          <p>Free shipping & 30 days money-back guarantee</p>
          <button className="sale-button" onClick={handleShopNow}>
            Shop Now →
          </button>
        </div>
      </section>
    </div>
  );
};

export default Home;
