package Group3.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import Group3.Enum.PayMethod;
import Group3.Enum.StateOrder;
import Group3.Enum.StateTransaction;
import Group3.dto.OrderItemResponseDTO;
import Group3.exception.CartItemNotFoundException;
import Group3.exception.EmailAlreadyExistsException;
import Group3.exception.OrderNotFoundException;
import Group3.model.Accounts;
import Group3.model.CartItems;
import Group3.model.OrderItems;
import Group3.model.Orders;
import Group3.model.Transaction;
import Group3.repository.AccountsRepository;
import Group3.repository.CartItemsRepository;
import Group3.repository.OrdersItemRepository;
import Group3.repository.OrdersRepository;
import Group3.repository.TransactionRepository;
import Group3.utility.SecurityUtil;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService implements OrderInterface {
    private AccountsRepository accRepo;
    private OrdersRepository orderRepo;
    private OrdersItemRepository orderItemRepo;
    private TransactionRepository transactionRepo;
    private EmailService emailService;
    private CartItemsRepository cartItemsRepo;
    private ProductInterface productI;
    

    public OrderService(AccountsRepository accRepo, OrdersRepository orderRepo, OrdersItemRepository orderItemRepo,
            TransactionRepository transactionRepo, EmailService emailService, CartItemsRepository cartItemsRepo,
            ProductInterface productI) {
        this.accRepo = accRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.transactionRepo = transactionRepo;
        this.emailService = emailService;
        this.cartItemsRepo = cartItemsRepo;
        this.productI = productI;
    }

    @Override
    public void seviceConfirmOrder(List<UUID> listCartItem) {
        UUID accountId = SecurityUtil.getCurrentUserId();
        Accounts accounts = accRepo.findByAccountId(accountId).orElseThrow(
                () -> new EmailAlreadyExistsException("Order service not give account"));
        List<CartItems> listC = listCartItem.stream()
                .map(this::mapCartItems).toList();
        Orders orders = saveOrder(accounts);
        saveOrderItem(listC, orders);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItems c:listC) {
            totalAmount = totalAmount.add(c.getPrice());
            productI.updateQuantity(c.getProduct().getQuantity()-c.getQuantity(),c.getProduct().getProductId());
        }
        saveTransaction(accounts, orders, totalAmount);
        List<OrderItems> listOrderItems = orderItemRepo.findByOrder(orders);
        try {
            emailService.sendOrderConfirmationEmail(
                    accounts.getEmail(),
                    accounts.getName(),
                    orders.getOrderId().toString(),
                    listOrderItems);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send order confirmation email", e);
        }

    }
     @Override
    public List<OrderItemResponseDTO> getOderItem(UUID orderId) {
        Orders orders=orderRepo.findById(orderId).orElseThrow(
            ()-> new OrderNotFoundException("Order id:"+ orderId +" not found")
        );
        List<OrderItems> response = orderItemRepo.findByOrder(orders);
        return response.stream()
        .map(this::mapOrderItemResponse)
        .toList();    
    }
    private OrderItemResponseDTO mapOrderItemResponse(OrderItems orderItems){
        OrderItemResponseDTO response = OrderItemResponseDTO.builder()
        .productId(orderItems.getProduct().getProductId())
        .name(orderItems.getProduct().getName())
        .image(orderItems.getProduct().getImage_url())
        .price(orderItems.getPrice())
        .quantity(orderItems.getQuantity())
        .build();
        return response;
    }

    private Orders saveOrder(Accounts accounts) {
        Orders newOrders = Orders.builder()
                .accounts(accounts)
                .createdAt(LocalDateTime.now())
                .completedAt(LocalDateTime.now())
                .state(StateOrder.SUCCESS)
                .build();
        return orderRepo.save(newOrders);
    }

    private void saveOrderItem(List<CartItems> listCartItems, Orders orders) {
        for (CartItems c: listCartItems) {
            OrderItems orderItems = OrderItems.builder()
                    .order(orders)
                    .product(c.getProduct())
                    .quantity(c.getQuantity())
                    .price(c.getPrice())
                    .build();
            orderItemRepo.save(orderItems);
        }
    }

    private void saveTransaction(Accounts accounts, Orders orders, BigDecimal amount) {
        Transaction transaction = Transaction.builder()
                .account(accounts)
                .order(orders)
                .amount(amount)
                .state(StateTransaction.SUCCESS)
                .payMethod(PayMethod.COD)
                .time(LocalDateTime.now())
                .build();
        transactionRepo.save(transaction);
    }

    private CartItems mapCartItems(UUID cartItemId) {
        return cartItemsRepo.findById(cartItemId).orElseThrow(
            ()-> new CartItemNotFoundException("Cart item: "+cartItemId+" not found")
        );
    }

}
