import React, { useState } from "react";
import { loginAPI, loginWithGGAPI, registerAPI } from "../api/authAPI"; // đảm bảo đường dẫn đúng
import "../assets/Styles/SignIn.css";
import logo from "../assets/LogoSignIn.png";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/UseAuthContext";
import config from "../configs";
import { toast } from "react-toastify";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [isSignUpMode, setIsSignUpMode] = useState(false);
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isSignUpMode) {
        // Đăng ký
        if (password !== confirmPassword) {
          toast.warn("Password and email do not match!");
          return;
        }

        const result = await registerAPI(email, password);
        toast.success("Registration successful!");
        console.log("Kết quả đăng ký:", result);

        // Chuyển sang trang đăng nhập
        setIsSignUpMode(false);
        setPassword("");
        setConfirmPassword("");
      } else {
        // Đăng nhập
        const result = await loginAPI(email, password);
        toast.success("Sign in successful!");
        login(result.accessTokenRes, result.refreshTokenRes);
        // navigate('/Profile');
        navigate("/Profile");
      }
    } catch (error) {
      console.error("Lỗi:", error);
      toast.warn("Password and email do not match!");
      toast.error("Error: " + (error?.response?.data?.message || error.message));
    }
  };
  // xử lý đăng nhập bằng google
  const handleGoogleLogin = () => {
    if (window.google) {
      const tokenClient = window.google.accounts.oauth2.initTokenClient({
        client_id:
          `${config.clientIdGg}`,
        scope: "email profile",
        callback: async (response) => {
          if (response.error) {
            console.error("Google login error:", response.error);
            return;
          }
          if (!response.access_token) {
            console.error("No access token received");
            return;
          }
        
          try {
            console.log(response.access_token)
            const result = await loginWithGGAPI(response.access_token);
            login(result.accessTokenRes, result.refreshTokenRes);
            navigate('/Profile');
          } catch (err) {
            console.error("Failed to get info from backend:", err);
          }
        },
      });

      tokenClient.requestAccessToken();
    } else {
      console.error("Google API not loaded");
    }
  };

  return (
    <div className="signin-container">
      <div className="signin-box">
        <img src={logo} alt="Logo" className="logoS" />

        <form onSubmit={handleSubmit} className="form-container">
          {/* Email */}
          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              placeholder="Enter Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          {/* Password */}
          <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              placeholder="Enter password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          {/* Confirm Password (chỉ hiện khi đăng ký) */}
          {isSignUpMode && (
            <div className="form-group">
              <label htmlFor="confirmPassword">Confirm password:</label>
              <input
                type="password"
                id="confirmPassword"
                placeholder="Enter password again"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
            </div>
          )}

          {/* Nút submit */}
          <button type="submit" className="login-btn">
            {isSignUpMode ? "Sign up" : "Sign in"}
          </button>
        </form>

        {/* Google login (placeholder) */}
        <div className="google-login">
          <button onClick={handleGoogleLogin} className="google-btn">
            Sign in with Google
          </button>
        </div>

        {/* Chuyển chế độ / Quên mật khẩu */}
        <div className="extra-buttons">
          <button
            className="signup-btn"
            onClick={() => setIsSignUpMode(!isSignUpMode)}
          >
            {isSignUpMode ? "Return to Sign In" : "Register"}
          </button>

          {!isSignUpMode && (
            <button className="forget-btn" onClick={() => navigate("/reset")}>
              Forgot Password?
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default SignIn;
