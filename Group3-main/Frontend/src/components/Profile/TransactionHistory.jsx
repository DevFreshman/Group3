import React, { useEffect, useState } from "react";
import "../../assets/Styles/Profile/TransactionHistory.css";
import { transactionAPI } from "../../api/transactionAPI";
import { getOrderItemAPI } from "../../api/orderAPI";

const TransactionHistory = () => {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [orderItems, setOrderItems] = useState([]);
  const [orderLoading, setOrderLoading] = useState(false);
  const [orderError, setOrderError] = useState(null);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  useEffect(() => {
  const fetchTransactions = async () => {
    try {
      const data = await transactionAPI();

      // Sắp xếp theo thời gian giảm dần
      const sortedData = data.sort((a, b) => new Date(b.time) - new Date(a.time));

      setTransactions(sortedData);
    } catch (err) {
      console.error("Error fetching transactions:", err);
      setError("Failed to load transaction history.");
    } finally {
      setLoading(false);
    }
  };

  fetchTransactions();
}, []);

  const handleOrderClick = async (orderId) => {
    setSelectedOrderId(orderId);
    setOrderLoading(true);
    setOrderError(null);
    try {
      const data = await getOrderItemAPI(orderId);
      setOrderItems(data);
    } catch (err) {
      console.error("Error fetching order items:", err);
      setOrderError("Failed to load order items.");
      setOrderItems([]);
    } finally {
      setOrderLoading(false);
    }
  };

  const closeModal = () => {
    setSelectedOrderId(null);
    setOrderItems([]);
    setOrderError(null);
  };

  const totalPages = Math.ceil(transactions.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentTransactions = transactions.slice(startIndex, startIndex + itemsPerPage);

  const goToPage = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  return (
    <div className="transaction-history">
      <h2>Transaction History</h2>

      {loading && <p>Loading transactions...</p>}
      {error && <p className="error">{error}</p>}

      {!loading && !error && transactions.length === 0 && (
        <p>No transactions found.</p>
      )}

      {!loading && !error && transactions.length > 0 && (
        <>
          <table className="transaction-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Amount</th>
                <th>State</th>
                <th>Pay Method</th>
                <th>Time</th>
                <th>Order ID</th>
              </tr>
            </thead>
            <tbody>
              {currentTransactions.map((tx) => (
                <tr key={tx.id}>
                  <td>{tx.id}</td>
                  <td>
                    {typeof tx.amount === "number"
                      ? tx.amount.toLocaleString("en-US", {
                          style: "currency",
                          currency: "USD",
                        })
                      : "N/A"}
                  </td>
                  <td>{tx.state || "N/A"}</td>
                  <td>{tx.payMethod || "N/A"}</td>
                  <td>{tx.time ? new Date(tx.time).toLocaleString() : "N/A"}</td>
                  <td>
                    {tx.order ? (
                      <button
                        className="order-id-button"
                        onClick={() => handleOrderClick(tx.order)}
                      >
                        {tx.order}
                      </button>
                    ) : (
                      "N/A"
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="pagination-controls">
            <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>
              Previous
            </button>
            <span>
              Page {currentPage} of {totalPages}
            </span>
            <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>
              Next
            </button>
          </div>
        </>
      )}

      {selectedOrderId && (
        <div className="modal-overlay" onClick={closeModal}>
          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()}
            role="dialog"
            aria-modal="true"
            aria-labelledby="modal-title"
          >
            <button aria-label="Close modal" onClick={closeModal}>
              &times;
            </button>
            <h3 id="modal-title">Order Details: {selectedOrderId}</h3>

            {orderLoading && <p>Loading order items...</p>}
            {orderError && <p className="error">{orderError}</p>}

            {!orderLoading && !orderError && orderItems.length === 0 && (
              <p>No items found for this order.</p>
            )}

            {!orderLoading && !orderError && orderItems.length > 0 && (
              <table>
                <thead>
                  <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                  </tr>
                </thead>
                <tbody>
                  {orderItems.map((item) => (
                    <tr key={item.productId}>
                      <td>
                        <img src={item.image} alt={item.name} />
                      </td>
                      <td>{item.name}</td>
                      <td className="quantity">{item.quantity}</td>
                      <td className="price">
                        {typeof item.price === "number"
                          ? item.price.toLocaleString("en-US", {
                              style: "currency",
                              currency: "USD",
                            })
                          : item.price.toString()}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionHistory;
