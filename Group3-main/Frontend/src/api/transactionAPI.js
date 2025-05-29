import configs from "../configs"
import axiosInstance from "./axiosIterceptor"

export const transactionAPI = async () => {
    const response = await axiosInstance.get(`${configs.transactionServiceUrl}`)
    return response.data
}
