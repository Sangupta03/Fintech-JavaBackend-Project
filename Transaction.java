package com.wallet.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_wallet_id")
    private Wallet senderWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_wallet_id")
    private Wallet receiverWallet;

    private String description;

    private String referenceNumber;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal balanceAfterTransaction;

    public enum TransactionType {
        CREDIT,       // Add money to wallet
        DEBIT,        // Withdraw money
        TRANSFER,     // Wallet to wallet
        REFUND        // Refund transaction
    }

    public enum TransactionStatus {
        PENDING, SUCCESS, FAILED, REVERSED
    }
}