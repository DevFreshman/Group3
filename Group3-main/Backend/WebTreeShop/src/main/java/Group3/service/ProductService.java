package Group3.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import Group3.Enum.ProductCategory;
import Group3.Enum.ProductType;
import Group3.exception.ProductNotFoundException;
import Group3.model.Products;
import Group3.repository.ProductsReposity;

@Service
public class ProductService implements ProductInterface {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductsReposity pRepo;

    public ProductService(ProductsReposity pRepo) {
        this.pRepo = pRepo;
    }

    @Override
    public List<Products> getAllProducts() {
        logger.info("Enter Service");
        return pRepo.findAll();
    }

    @Override
    public List<Products> searchProducts(String name, List<ProductCategory> categories, List<ProductType> types) {
        return pRepo.searchProducts(name, categories, types);
    }

    @Override
    public Products geProductById(UUID productId) {
        return pRepo.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product:" + productId + ""));
    }

    @Override
    public void updateQuantity(int quantity, UUID productId) {
        Products products = pRepo.findByProductId(productId).orElseThrow(
                () -> new ProductNotFoundException("Product: " + productId + " not found"));

        products.setQuantity(quantity);
        pRepo.save(products);
    }
}
