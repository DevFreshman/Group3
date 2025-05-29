import React, { useState, useEffect } from "react";
import ProductCard from "../components/Product/ProductCard";
import ProductFilter from "../components/Product/ProductFilter";
import ProductDetail from "../components/Product/ProductDetail";
import { getAllProductAPI, searchProductsAPI } from "../api/productAPI";

import "../assets/Styles/Product/ProductPage.css";
import ProductNotFound from "../components/Product/ProductNotFound";

const PRODUCTS_PER_PAGE = 9;

const Product = () => {
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    fetchAllProducts();
  }, []);

  const fetchAllProducts = async () => {
    try {
      const data = await getAllProductAPI();
      setProducts(data);
    } catch (error) {
      console.error("Failed to fetch products:", error);
    }
  };

  const handleFilterChange = async ({ searchText, types }) => {
    try {
      const filtered = await searchProductsAPI({
        name: searchText,
        types,
        categories: [] // Nếu cần lọc theo category, bạn truyền vào sau
      });

      setProducts(filtered);
      setCurrentPage(1);
    } catch (error) {
      console.error("Failed to filter products:", error);
    }
  };

  const totalPages = Math.ceil(products.length / PRODUCTS_PER_PAGE);

  const productsToShow = products.slice(
    (currentPage - 1) * PRODUCTS_PER_PAGE,
    currentPage * PRODUCTS_PER_PAGE
  );

  const goToPage = (pageNum) => {
    if (pageNum < 1 || pageNum > totalPages) return;
    setCurrentPage(pageNum);
  };

  return (
    <div className="product-page">
      <ProductFilter onFilterChange={handleFilterChange} />
      
      <div className="product-list">
        {productsToShow.length > 0 ? (
          productsToShow.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <ProductNotFound/>
        )}
      </div>

      <div className="pagination">
        <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>
          Prev
        </button>
        <span>Page {currentPage} of {totalPages}</span>
        <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>
          Next
        </button>
      </div>
    </div>
  );
};

export default Product;
