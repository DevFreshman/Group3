package Group3.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group3.model.CartItems;
import jakarta.transaction.Transactional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, UUID> {

    @Query("SELECT c FROM CartItems c WHERE c.account.accountId = :accountId")
    List<CartItems> findByAccountId(@Param("accountId") UUID accountId);

    @Query("SELECT c FROM CartItems c WHERE c.account.accountId = :accountId AND c.product.productId = :productId")
    Optional<CartItems> findByAccountIdAndProductId(@Param("accountId") UUID accountId,
            @Param("productId") UUID productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItems c WHERE c.account.accountId = :accountId")
    void deleteByAccountId(@Param("accountId") UUID accountId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItems c WHERE c.id IN :list")
    void deleteListCartItem(@Param("list") List<UUID> list);
}
