package com.wallet.repository;

import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(String transactionId);

    @Query("SELECT t FROM Transaction t WHERE t.senderWallet = :wallet OR t.receiverWallet = :wallet ORDER BY t.transactionDate DESC")
    Page<Transaction> findAllByWallet(@Param("wallet") Wallet wallet, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE (t.senderWallet = :wallet OR t.receiverWallet = :wallet) " +
           "AND t.transactionDate BETWEEN :startDate AND :endDate ORDER BY t.transactionDate DESC")
    List<Transaction> findByWalletAndDateRange(
            @Param("wallet") Wallet wallet,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.receiverWallet = :wallet AND t.type = 'CREDIT' AND t.status = 'SUCCESS'")
    BigDecimal getTotalCredits(@Param("wallet") Wallet wallet);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.senderWallet = :wallet AND t.status = 'SUCCESS'")
    BigDecimal getTotalDebits(@Param("wallet") Wallet wallet);
}