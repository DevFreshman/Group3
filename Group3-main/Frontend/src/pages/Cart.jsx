import CartSummary from "../components/Cart/CartSummary";
import CartItem from "../components/Cart/CartItem";
import { useCart } from "../context/UseCartContext";
import CartHeader from "../components/Cart/CartHeader";
import EmptyCart from "../components/Cart/EmptyCart";
import "../assets/Styles/Cart/Cart.css"
import { useEffect, useState } from "react";
import { getAllCartAPI } from "../api/cartAPI";

const Cart = () => {
  const { cartItems, setCartItems } = useCart();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCartItems = async () => {
      setLoading(true);
      try {
        const data = await getAllCartAPI();
        setCartItems(data);
      } catch (error) {
        console.error("Failed to fetch cart items:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchCartItems();
  }, [setCartItems]);

  if (loading) return <h2>Loading cart items...</h2>;

  return (
    <div className="cart-page">
      {cartItems.length === 0 ? (
        <EmptyCart />
      ) : (
        <>
          <CartHeader />
          <div className="cart-items">
            {cartItems.map(item => (
              <CartItem key={item.cart_item_id} item={item} />
            ))}
          </div>
          <CartSummary />
        </>
      )}
    </div>
  );
};

export default Cart;
