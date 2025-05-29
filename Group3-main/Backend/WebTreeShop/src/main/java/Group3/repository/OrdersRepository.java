package Group3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group3.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,UUID>{
}
