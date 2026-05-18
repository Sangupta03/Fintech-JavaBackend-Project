package com.wallet.serviceimpl;

import com.wallet.config.JwtUtil;
import com.wallet.dto.WalletDto.*;
import com.wallet.exception.WalletException;
import com.wallet.model.User;
import com.wallet.model.Wallet;
import com.wallet.repository.UserRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Validate uniqueness
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new WalletException("Email already registered: " + request.getEmail());
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new WalletException("Phone number already registered");
        }

        // Create user
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(User.Role.USER)
                .isActive(true)
                .build();
        userRepository.save(user);

        // Create wallet for the user
        String walletNumber = generateWalletNumber();
        Wallet wallet = Wallet.builder()
                .walletNumber(walletNumber)
                .balance(BigDecimal.ZERO)
                .status(Wallet.WalletStatus.ACTIVE)
                .user(user)
                .build();
        walletRepository.save(wallet);

        // Generate JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        log.info("User registered successfully: {}", user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .walletNumber(walletNumber)
                .balance(BigDecimal.ZERO)
                .message("Registration successful! Your wallet has been created.")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new WalletException("User not found"));

        if (!user.isActive()) {
            throw new WalletException("Account is deactivated. Please contact support.");
        }

        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new WalletException("Wallet not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        log.info("User logged in: {}", user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .walletNumber(wallet.getWalletNumber())
                .balance(wallet.getBalance())
                .message("Login successful!")
                .build();
    }

    private String generateWalletNumber() {
        String walletNumber;
        do {
            walletNumber = "WLT" + System.currentTimeMillis() + (int)(Math.random() * 1000);
        } while (walletRepository.existsByWalletNumber(walletNumber));
        return walletNumber;
    }
}