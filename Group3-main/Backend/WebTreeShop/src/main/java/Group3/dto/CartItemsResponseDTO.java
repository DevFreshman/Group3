package Group3.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemsResponseDTO {
    private UUID cart_item_id;
    private String name;
    private BigDecimal price;
    private BigDecimal price_cart;
    private String image;
    private int stock_quantity;
    private int quantity;
}
