package Group3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group3.dto.AddToCartRequestDTO;
import Group3.dto.CartItemsResponseDTO;
import Group3.dto.ChangeCartItemRequestDTO;
import Group3.dto.DeleteListCartItemDTO;
import Group3.service.CartService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ResponseEntity<List<CartItemsResponseDTO>> getAllItem() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @PostMapping()
    public ResponseEntity<String> addToCart(@RequestBody @Valid AddToCartRequestDTO request) {
        cartService.insertCartItem(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Product added to cart successfully");
    }
    
    @PutMapping()
    public ResponseEntity<String> increaseQuantity(@RequestBody @Valid ChangeCartItemRequestDTO request) {
        cartService.changeCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return ResponseEntity.ok("Quantity updated successfully.");
    }

    @DeleteMapping()
    public ResponseEntity<String> clearCart() {
        cartService.deleteByAccountId();
        return ResponseEntity.ok("Cart cleared.");
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable UUID cartItemId) {
        cartService.deleteByCartItemId(cartItemId);
        return ResponseEntity.ok("Item deleted.");
    }
    @DeleteMapping("/list")
    public ResponseEntity<String> deleteListCartItem(@RequestBody DeleteListCartItemDTO requets){
        cartService.deleteListCartItem(requets.getList());
        return ResponseEntity.ok("Item deleted");
    }
    
}
