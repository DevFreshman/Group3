import { useState } from "react";
import "../assets/Styles/Reset.css";

export default function RequestResetPassword() {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");

    if (!email) {
      setError("Required");
      return;
    }

    try {
      // gọi API để đổi lại mật
      setMessage("Đã gửi email chứa liên kết đặt lại mật khẩu.");
    } catch (error) {
      setError("Không thể gửi yêu cầu. Kiểm tra lại email.",error);
    }
  };

  return (
    <div className="reset-container">
      <h2>Password Reset</h2>
      <div className="instruction">
        Forgotten your password? Enter your e-mail address below, and we'll send you an e-mail allowing you to reset it.
      </div>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="E-mail address"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        {error && <div className="error">{error}</div>}
        <button type="submit">Reset My Password</button>
        {message && <p className="success">{message}</p>}
      </form>
    </div>
  );
}
