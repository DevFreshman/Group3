import configs from "../configs";
import axiosInstance from "./axiosIterceptor"

// lấy dữ liệu người dùng
export const getUserDetailAPI = async() => {
    const response = await axiosInstance.get(`${configs.profileServiceUrl}/get/info`);
    return response.data;
};

// lưu dữ liệu người dùng
export const saveUserDetailAPI = async(field, value)=>{
    const response = await axiosInstance.post(`${configs.profileServiceUrl}/save/info`,{
        field,
        value,
    });
    return response.data;
}
