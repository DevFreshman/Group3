// src/components/Cart/CartSummary.jsx
import React, { useState } from "react";
import { useCart } from "../../context/UseCartContext";
import "../../assets/Styles/Cart/CartSummary.css"
import { deleteListCartItemAPI } from "../../api/cartAPI";
import PurchaseModal from "./PurchaseModal";
import { toast } from "react-toastify";
import { sendEmailAPI } from "../../api/orderAPI";


const CartSummary = () => {
  const { cartItems, selectedItems, selectAll, removeSelectedItems } = useCart();
  const [showConfirmModal, setShowConfirmModal] = useState(false);

  const selectedCartItems = cartItems.filter((item) =>
    selectedItems.includes(item.cart_item_id)
  );

  const total = selectedCartItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  const handleDelete = async (list) => {
    await deleteListCartItemAPI(list);
    removeSelectedItems();
  };

  const handlePurchase = () => {
    if (selectedItems.length === 0) {
      toast.warning("Please select at least one item.");
      return;
    }
    setShowConfirmModal(true);
  };

  const confirmPurchase = async () => {
    toast.success("Purchase confirmed! Please check your email");
    setShowConfirmModal(false);
    await sendEmailAPI(selectedItems)
  };

  return (
    <>
      <div className="cart-footer fixed">
        <div className="left">
          <label>
            <input
              type="checkbox"
              checked={selectedItems.length === cartItems.length}
              onChange={selectAll}
            />
            {" "} Select all ({selectedItems.length})
          </label>
          <button className="btn-link" onClick={() => handleDelete(selectedItems)}>
            Remove
          </button>
        </div>
        <div className="right">
          <span className="total-text">Total:</span>
          <span className="total-amount">$ {total.toLocaleString()}</span>
          <button className="btn-buy" onClick={handlePurchase}>Purchase</button>
        </div>
      </div>

      {showConfirmModal && (
        <PurchaseModal
          items={selectedCartItems}
          total={total}
          onConfirm={confirmPurchase}
          onCancel={() => setShowConfirmModal(false)}
        />
      )}
    </>
  );
};

export default CartSummary;