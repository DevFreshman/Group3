package Group3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group3.model.Accounts;
import Group3.model.Transaction;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,UUID> {
    List<Transaction> findByAccount(Accounts account);
}
