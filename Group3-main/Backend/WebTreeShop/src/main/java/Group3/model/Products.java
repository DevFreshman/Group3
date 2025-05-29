package Group3.model;

import java.math.BigDecimal;
import java.util.UUID;

import Group3.Enum.ProductCategory;
import Group3.Enum.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Products {
    @Id
    @Column(columnDefinition = "BINARY(16)", name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(precision = 10, scale = 2, name = "price")
    private BigDecimal price;

    @Column(columnDefinition = "TEXT", name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Column(name = "image_url")
    private String image_url;
}
