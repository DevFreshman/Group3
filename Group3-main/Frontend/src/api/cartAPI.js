//Get all item in cart
//Remove all item in cart of user
//Remove one item in cart of user
//Add one item in cart of user
//Remove quanity of one item in cart of user
import axiosInstance from "./axiosIterceptor";
import configs from "../configs";

export const getAllCartAPI = async () =>{
    const response = await axiosInstance.get(`${configs.cartServiceUrl}`);
    return response.data;
}

export const addProductToCart = async (productId, quantity) =>{
    const response = await axiosInstance.post(`${configs.cartServiceUrl}`,{
        productId,
        quantity
    });
    return response.data;
}

export const increaseProduct = async (cartItemId, quantity) =>{
    const response = await axiosInstance.put(`${configs.cartServiceUrl}`,{
        cartItemId,
        quantity
    });
    return response.data;
}

export const deleteProductAPI = async (cartItemId) => {
    const response = await axiosInstance.delete(`${configs.cartServiceUrl}/${cartItemId}`);
    return response.data;
}

export const deleteListCartItemAPI = async (list) => {
    const response = await axiosInstance.delete(`${configs.cartServiceUrl}/list`, {
        data: { list }
    });
    return response.data;
}
