package Group3.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Group3.Enum.ProductCategory;
import Group3.Enum.ProductType;
import Group3.model.Products;
import Group3.service.ProductInterface;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductInterface productService;

    public ProductController(ProductInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<Products> getAll() {
        logger.info("Enter API");
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Products> searchProducts(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) List<ProductCategory> categories,
        @RequestParam(required = false) List<ProductType> types
    ) {
        return productService.searchProducts(name, categories, types);
    }

       @GetMapping("/{id}")
    public ResponseEntity<Products> getProductByIdAPI(@PathVariable UUID id) {
        logger.info("id {}"+id);

        return ResponseEntity.ok(productService.geProductById(id));
    }
}
