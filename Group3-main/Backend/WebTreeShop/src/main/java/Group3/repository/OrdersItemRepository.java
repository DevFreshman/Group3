package Group3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Group3.model.OrderItems;
import Group3.model.Orders;

import java.util.List;


public interface OrdersItemRepository extends JpaRepository<OrderItems,UUID> {
    List<OrderItems> findByOrder(Orders order);
}
