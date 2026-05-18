package com.wallet.repository;
 
import com.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.Optional;
 
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByWalletNumber(String walletNumber);
    Optional<Wallet> findByUserId(Long userId);
    boolean existsByWalletNumber(String walletNumber);
}
 