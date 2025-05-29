package Group3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import Group3.Enum.ProductCategory;
import Group3.Enum.ProductType;
import Group3.model.Products;

@Component
public interface ProductInterface {
    Products geProductById(UUID productId);

    List<Products> getAllProducts();

    void updateQuantity(int quantity, UUID Product);

    List<Products> searchProducts(String name, List<ProductCategory> categories, List<ProductType> types);
}
