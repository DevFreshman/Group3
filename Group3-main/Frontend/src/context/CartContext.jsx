import React, { createContext, useState } from "react";

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);

  const [selectedItems, setSelectedItems] = useState([]);

  const toggleSelectItem = (id) => {
    setSelectedItems((prev) =>
      prev.includes(id) ? prev.filter((itemId) => itemId !== id) : [...prev, id]
    );
  };
  const selectAll = () => {
    if (selectedItems.length === cartItems.length) {
      setSelectedItems([]);
    } else {
      setSelectedItems(cartItems.map((item) => item.cart_item_id));
    }
  };
  const removeSelectedItems = () => {
    setCartItems((prev) => prev.filter((item) => !selectedItems.includes(item.cart_item_id)));
    setSelectedItems([]);
  };
const updateQuantity = (id, quantity) => {
  setCartItems((prev) =>
    prev.map((item) =>
      item.cart_item_id === id
        ? {
            ...item,
            quantity: Math.max(1, quantity),
            price_cart: item.price * Math.max(1, quantity),  // cập nhật tổng tiền luôn
          }
        : item
    )
  );
};

  const removeItem = (id) => {
    setCartItems((prev) => prev.filter((item) => item.cart_item_id !== id));
    setSelectedItems((prev) => prev.filter((itemId) => itemId !== id));
  };
  

  return (
    <CartContext.Provider
      value={{
        cartItems,
        setCartItems,
        selectedItems,
        toggleSelectItem,
        selectAll,
        removeSelectedItems,
        updateQuantity,
        removeItem,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export default CartContext
