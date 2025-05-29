package Group3.service;

import java.util.List;
import java.util.UUID;

import Group3.dto.OrderItemResponseDTO;

public interface OrderInterface {
    //Create oder and oderItem and Transaction 
    //Call Email service
    void seviceConfirmOrder(List<UUID> listCartItem);
    //GetOrderItem
    List<OrderItemResponseDTO> getOderItem(UUID oderId);
}
