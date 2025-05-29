package Group3.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import Group3.dto.CartItemsResponseDTO;
import Group3.exception.*;
import Group3.model.Accounts;
import Group3.model.CartItems;
import Group3.model.Products;
import Group3.repository.AccountsRepository;
import Group3.repository.CartItemsRepository;
import Group3.repository.ProductsReposity;
import Group3.utility.SecurityUtil;
import jakarta.transaction.Transactional;

@Service
public class CartService implements CartInterface {
    private CartItemsRepository cartItemsRepo;
    private AccountsRepository accountsRepo;
    private ProductsReposity productsRepo;

    public CartService(CartItemsRepository cartItemsRepo, AccountsRepository accountsRepo,
            ProductsReposity productsRepo) {
        this.cartItemsRepo = cartItemsRepo;
        this.accountsRepo = accountsRepo;
        this.productsRepo = productsRepo;
    }

    @Override
    public List<CartItemsResponseDTO> getAll() {
    UUID accountId = SecurityUtil.getCurrentUserId();
    List<CartItems> cartItemsList = cartItemsRepo.findByAccountId(accountId);

    // Kiểm tra và sửa lại quantity nếu cần
    for (CartItems cartItem : cartItemsList) {
        Products product = cartItem.getProduct();
        if (cartItem.getQuantity() > product.getQuantity()) {
            // Điều chỉnh lại quantity trong cartItem
            cartItem.setQuantity(product.getQuantity());
            cartItem.setPrice(calculatePrice(product, product.getQuantity()));
            cartItemsRepo.save(cartItem);
        }
    }

    return cartItemsList.stream()
            .map(this::mapDTO)
            .toList();
}
    private CartItemsResponseDTO mapDTO(CartItems c) {
        Products p = c.getProduct();
        return CartItemsResponseDTO.builder()
                .cart_item_id(c.getCartItemId())
                .name(p.getName())
                .price(p.getPrice())
                .price_cart(c.getPrice())
                .image(p.getImage_url())
                .stock_quantity(p.getQuantity())
                .quantity(c.getQuantity())
                .build();
    }

    @Override
    public void deleteByCartItemId(UUID cartId) {
        cartItemsRepo.deleteById(cartId);
    }

    @Override
    public void deleteByAccountId() {
        UUID accUuid = SecurityUtil.getCurrentUserId();
        cartItemsRepo.deleteByAccountId(accUuid);
    }

    @Override
    @Transactional
    public void changeCartItemQuantity(UUID cartItemsId, int quantity) {
        CartItems cartItems = cartItemsRepo.findById(cartItemsId).orElseThrow(
                () -> new CartItemNotFoundException("Cart item with ID " + cartItemsId + " not found"));
        Products p = cartItems.getProduct();
        int newQuantity = quantity + cartItems.getQuantity();
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (isOutOfStock(newQuantity, p))
            throw new OutOfStockException("request: " + newQuantity + " available quantity: " + p.getQuantity());
        cartItems.setQuantity(newQuantity);
        cartItems.setPrice(calculatePrice(p, newQuantity));
        cartItemsRepo.save(cartItems);
    }

    @Override
    @Transactional
    public void insertCartItem(UUID productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        Products product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        UUID accountId = SecurityUtil.getCurrentUserId();
        Accounts account = accountsRepo.findByAccountId(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Optional<CartItems> optCartItems = cartItemsRepo.findByAccountIdAndProductId(accountId, productId);
        if (optCartItems.isPresent()) {
            CartItems cartItems = optCartItems.get();
            int newQuantity = cartItems.getQuantity() + quantity;
            if (isOutOfStock(newQuantity, product)) {
                throw new OutOfStockException("Requested: " + newQuantity + ", Available: " + product.getQuantity());
            }
            cartItems.setQuantity(newQuantity);
            cartItems.setPrice(calculatePrice(product, newQuantity));
            cartItemsRepo.save(cartItems);
            return;
        }
        if (isOutOfStock(quantity, product)) {
            throw new OutOfStockException("request: " + quantity + " available quantity: " + product.getQuantity());
        }
        CartItems newItems = CartItems.builder()
                .account(account)
                .product(product)
                .quantity(quantity)
                .price(calculatePrice(product, quantity))
                .build();
        cartItemsRepo.save(newItems);
    }

    private BigDecimal calculatePrice(Products p, int quantity) {
        return p.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    // True if out of stock
    private boolean isOutOfStock(int quantity, Products p) {
        return quantity > p.getQuantity();
    }

    @Override
    public void deleteListCartItem(List<UUID> list) {
        cartItemsRepo.deleteListCartItem(list);
    }

}
