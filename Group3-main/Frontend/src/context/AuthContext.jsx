import React, { createContext, useState, useEffect } from 'react';
import {jwtDecode} from 'jwt-decode';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true); // loading

  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      try {
        const decoded = jwtDecode(accessToken);
        setUser(decoded);
      } catch (error) {
        console.error("Token không hợp lệ:", error);
        setUser(null);
      }
    }
    setLoading(false); // Kết thúc loading
  }, []);

  const login = (accessTokenRes, refreshTokenRes) => {
    localStorage.setItem('accessToken', accessTokenRes);
    localStorage.setItem('refreshToken', refreshTokenRes);
    const decodedUser = jwtDecode(accessTokenRes);
    setUser(decodedUser);
  };

  const logout = () => {
    setUser(null);
    localStorage.clear();
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
