package com.wallet.service;

import com.wallet.dto.WalletDto.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}