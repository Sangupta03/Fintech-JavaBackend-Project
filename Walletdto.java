package com.wallet.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletDto {

    // ===================== REQUEST DTOs =====================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegisterRequest {
        @NotBlank(message = "Full name is required")
        private String fullName;

        @Email(message = "Valid email is required")
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[6-9]\\d{9}$", message = "Valid 10-digit Indian phone number required")
        private String phoneNumber;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddMoneyRequest {
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.0", message = "Minimum amount is ₹1")
        @DecimalMax(value = "100000.0", message = "Maximum add per transaction is ₹1,00,000")
        private BigDecimal amount;

        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TransferRequest {
        @NotBlank(message = "Receiver wallet number is required")
        private String receiverWalletNumber;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.0", message = "Minimum transfer is ₹1")
        private BigDecimal amount;

        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class WithdrawRequest {
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1.0", message = "Minimum withdrawal is ₹1")
        private BigDecimal amount;

        private String description;
    }

    // ===================== RESPONSE DTOs =====================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AuthResponse {
        private String token;
        private String tokenType = "Bearer";
        private String email;
        private String fullName;
        private String walletNumber;
        private BigDecimal balance;
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class WalletResponse {
        private Long id;
        private String walletNumber;
        private BigDecimal balance;
        private String status;
        private String ownerName;
        private String ownerEmail;
        private LocalDateTime lastUpdated;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TransactionResponse {
        private Long id;
        private String transactionId;
        private BigDecimal amount;
        private String type;
        private String status;
        private String senderWalletNumber;
        private String receiverWalletNumber;
        private String description;
        private BigDecimal balanceAfterTransaction;
        private LocalDateTime transactionDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;

        public static <T> ApiResponse<T> success(String message, T data) {
            return new ApiResponse<>(true, message, data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }
    }
}