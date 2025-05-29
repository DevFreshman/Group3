package Group3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import Group3.dto.CartItemsResponseDTO;

@Component
public interface CartInterface {
//Get all items based on accountId return product details cart id
//Product details name , image, stock quantity, price 
List<CartItemsResponseDTO> getAll();
//Remove row by cartId
void deleteByCartItemId(UUID cartId);
//Remove all based on accountId
void deleteByAccountId();
//Add item in Cart
void insertCartItem(UUID productId, int quantity);
//Change product quantity in cart item
void changeCartItemQuantity(UUID cartItemId, int quantity);
//Remove list cart item 
void deleteListCartItem(List<UUID> list );
}

