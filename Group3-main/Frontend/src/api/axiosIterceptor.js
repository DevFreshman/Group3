// src/api/axiosInstance.js
import axios from 'axios';
import {jwtDecode} from 'jwt-decode';
import configs from '../configs'
const axiosInstance = axios.create({ 
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Hàm kiểm tra accessToken còn hạn không
const isTokenExpired = (token) => {
  if (!token) return true;
  try {
    const decoded = jwtDecode(token);
    return decoded.exp * 1000 < Date.now(); // hết hạn rồi
  } catch (err) {
    console.log("Hết hạn",err)
    return true;
  }
};

// Interceptor cho request
axiosInstance.interceptors.request.use(
  async (config) => {
    let accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    console.log(accessToken)
    console.log("refresh token: "+refreshToken)
    // Nếu accessToken hết hạn và có refreshToken => gọi API để lấy accessToken mới
    if (isTokenExpired(accessToken) && refreshToken) {
      try {
        const res = await axios.post(`${configs.authServiceUrl}/refresh`, {
          token:refreshToken,
        });

        accessToken = res.data.token;
        localStorage.setItem('accessToken', accessToken);
      } catch (error) {
        console.error('Refresh token hết hạn hoặc không hợp lệ');
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        throw error;
      }
    }

    if (accessToken) {
      console.log(accessToken)
      config.headers['Authorization'] = `Bearer ${accessToken}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// Interceptor cho response
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      console.error('Server trả về lỗi:', error.response.data.message || error.response.statusText);
    } else if (error.request) {
      console.error('Không có phản hồi từ server.');
    } else {
      console.error('Lỗi khi cấu hình request:', error.message);
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
