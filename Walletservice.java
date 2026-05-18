package com.wallet.service;

import com.wallet.dto.WalletDto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WalletService {
    WalletResponse getWalletByUser(String email);
    WalletResponse getWalletByNumber(String walletNumber);
    TransactionResponse addMoney(String email, AddMoneyRequest request);
    TransactionResponse transferMoney(String email, TransferRequest request);
    TransactionResponse withdrawMoney(String email, WithdrawRequest request);
    Page<TransactionResponse> getTransactionHistory(String email, Pageable pageable);
    List<TransactionResponse> getTransactionsByDateRange(String email, String startDate, String endDate);
    ApiResponse<Object> getWalletSummary(String email);
}