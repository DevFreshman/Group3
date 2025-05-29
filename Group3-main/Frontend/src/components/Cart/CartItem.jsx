import React from "react";
import { useCart } from "../../context/UseCartContext";
import "../../assets/Styles/Cart/CartItem.css"
import { deleteProductAPI, increaseProduct } from "../../api/cartAPI";
import { toast } from "react-toastify";

const CartItem = ({ item }) => {
  const { selectedItems, toggleSelectItem, removeItem, updateQuantity } = useCart();

  const handleQuantityChange = async (delta) => {
    const newQuantity = item.quantity + delta;
    if (newQuantity < 1) return; // Không giảm dưới 1

    try {
      await increaseProduct(item.cart_item_id, delta);
      updateQuantity(item.cart_item_id, newQuantity);  // Cập nhật số lượng local state
    } catch (error) {
      console.error("Failed to update quantity:", error);
      toast.warn("Run out of product");
    }
  };
  const  handleRemoveItem = async (id)=>{
      await deleteProductAPI(id);
      removeItem(id);
  }
  
  return (
    <div className="cart-item">
      <input
        type="checkbox"
        checked={selectedItems.includes(item.cart_item_id)}
        onChange={() => toggleSelectItem(item.cart_item_id)}
      />
      <div className="col product">
        <img src={item.image} alt={item.name} />
        <div>
          <h4>{item.name}</h4>
        </div>
      </div>
      <div className="col price">$ {item.price.toLocaleString()}</div>
      <div className="col quantity">
        <button onClick={() => handleQuantityChange(-1)} disabled={item.quantity <= 1}>-</button>
        <input type="text" value={item.quantity} readOnly />
        <button onClick={() => handleQuantityChange(1)}>+</button>
      </div>
      <div className="col total">$ {(item.price_cart).toLocaleString()}</div>
      <div className="col remove" onClick={() => handleRemoveItem(item.cart_item_id)}>Remove</div>
    </div>
  );
};

export default CartItem;
