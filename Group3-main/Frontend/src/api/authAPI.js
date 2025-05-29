import axiosInstance from "./axiosIterceptor";
import configs from "../configs";

// Đăng nhập
export const loginAPI = async (email, password) => {
  const response = await axiosInstance.post(`${configs.authServiceUrl}/login`, {
    email,
    password,
  });


  const { accessTokenRes, refreshTokenRes } = response.data;
  localStorage.setItem('accessToken', accessTokenRes);
  localStorage.setItem('refreshToken', refreshTokenRes);

  return response.data;
};

// Đăng ký
export const registerAPI = async (email, password) => {
  const response = await axiosInstance.post(`${configs.authServiceUrl}/register`, {
    email,
    password,
  });

  return response.data;
};


// Lấy thông tin người dùng từ Google
export const loginWithGGAPI = async (accessToken) =>{
  const response = await axiosInstance.post(`${configs.authServiceUrl}/login/with/google`,{
    accessToken
  });
  return response.data;
}
