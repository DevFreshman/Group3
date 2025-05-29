package Group3.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import Group3.Enum.PayMethod;
import Group3.Enum.StateTransaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id", columnDefinition="BINARY(16)")
    private UUID transactionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "account_id", nullable = false)
    private Accounts account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "order_id", nullable = false)
    private Orders order;
    
    @Column(precision = 10,scale = 2,name = "amount")
    private BigDecimal amount;

    @Column(name = "state", columnDefinition = "VARCHAR(25)")
    @Enumerated(EnumType.STRING)
    private StateTransaction state;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "pay_method", columnDefinition = "VARCHAR(25)")
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;
}