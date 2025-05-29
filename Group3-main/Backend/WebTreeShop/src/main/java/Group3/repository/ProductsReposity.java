package Group3.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import Group3.model.Products;
import Group3.Enum.ProductCategory;
import Group3.Enum.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsReposity extends JpaRepository<Products, UUID> {

    Optional<Products> findByProductId(UUID productId); 

    @Query("SELECT p FROM Products p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:categories IS NULL OR p.category IN :categories) AND " +
           "(:types IS NULL OR p.type IN :types)")
    List<Products> searchProducts(
        @Param("name") String name,
        @Param("categories") List<ProductCategory> categories,
        @Param("types") List<ProductType> types
    );
}
