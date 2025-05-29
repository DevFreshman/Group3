// Import cấu hình và axios interceptor
import configs from "../configs";
import axiosInstance from "./axiosIterceptor";
import qs from "qs";

// Lấy tất cả sản phẩm
export const getAllProductAPI = async () => {
  const response = await axiosInstance.get(`${configs.productServiceUrl}/getAll`);
  return response.data;
};

// Lấy sản phẩm theo ID
export const getProductByIdAPI = async (id) => {
  const response = await axiosInstance.get(`${configs.productServiceUrl}/${id}`);
  return response.data;
};

// Tìm kiếm sản phẩm
export const searchProductsAPI = async ({ name, categories, types }) => {
  const params = {};
  if (name) params.name = name;
  if (categories && categories.length > 0) params.categories = categories;
  if (types && types.length > 0) params.types = types;

  const response = await axiosInstance.get(`${configs.productServiceUrl}/search`, {
    params,
    paramsSerializer: (params) => qs.stringify(params, { arrayFormat: "repeat" }),
  });

  return response.data;
};