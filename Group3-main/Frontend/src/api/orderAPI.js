// Import cấu hình và axios interceptor
import configs from "../configs";
import axiosInstance from "./axiosIterceptor";

// thực hiện gửi Email
export const sendEmailAPI = async (list) => {
  const response = await axiosInstance.post(`${configs.orderServiceUrl}`,
    {list}
  );
  return response.data;
};
// lấy Item trong thanh toán
export const getOrderItemAPI= async(id) =>{
  const response = await axiosInstance.get(`${configs.orderServiceUrl}/${id}`);
  return response.data
}