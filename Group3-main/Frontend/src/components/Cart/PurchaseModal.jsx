// src/components/Cart/PurchaseModal.jsx
import React from "react";
import "../../assets/Styles/Cart/PurchaseModal.css";

const PurchaseModal = ({ items, total, onConfirm, onCancel }) => {
  return (
    <div className="modal-overlay">
      <div className="modal-card">
        <h2 className="modal-title">Confirm Your Order</h2>

        <div className="modal-body">
          <ul className="modal-item-list">
            {items.map((item) => (
              <li key={item.cart_item_id} className="modal-item">
                <img src={item.image} alt={item.name} className="item-image" />
                <div className="item-details">
                  <span className="item-name">{item.name}</span>
                  <span className="item-qty">x{item.quantity}</span>
                  <span className="item-total">
                    $ {(item.price * item.quantity).toLocaleString()}
                  </span>
                </div>
              </li>
            ))}
          </ul>

          <div className="modal-total">
            <span>Total:</span>
            <strong>$ {total.toLocaleString()}</strong>
          </div>
        </div>

        <div className="modal-actions">
          <button className="btn confirm" onClick={onConfirm}>Confirm</button>
          <button className="btn cancel" onClick={onCancel}>Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default PurchaseModal;
