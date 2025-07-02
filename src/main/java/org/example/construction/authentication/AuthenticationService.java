package org.example.construction.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.construction.cache.RedisTokenService;
import org.example.construction.cache.RedisVerificationCodeService;
import org.example.construction.dto.AuthRequest;
import org.example.construction.dto.AuthResponse;
import org.example.construction.dto.LogoutRequest;
import org.example.construction.dto.VerificationRequest;
import org.example.construction.enums.Role;
import org.example.construction.model.User;
import org.example.construction.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisVerificationCodeService verificationCodeService;
    private final JwtService jwtService;
    private final RedisTokenService redisTokenService;
    private final UserDetailsService userDetailsService;

    public String register(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return "Registered successfully";
    }

    public AuthResponse refreshAuthToken(HttpServletRequest request) throws IOException {
        String userEmail;
        String refreshToken;
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.validateToken(user, refreshToken)) {
                String accessToken = jwtService.generateAccessToken(userEmail, user.getAuthorities().toString());
                return AuthResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(accessToken).build();
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public void logout(LogoutRequest request) {
        var token = request.getRefreshToken();
        String email = request.getEmail();
        if (redisTokenService.validateRefreshToken(email, token)) {
            redisTokenService.deleteRefreshToken(email);
        } else {
            throw new RuntimeException("Logout failed");
        }
    }

    public Boolean verify(VerificationRequest verificationRequest) {
        userRepository.findByUsername(verificationRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        return verificationCodeService.codeIsValid(verificationRequest.getVerificationCode(), verificationRequest.getEmail());
    }

    public AuthResponse resetEmail(String email, CustomUserDetails userDetails) {
        User user = null;
        if (userDetails != null) {
            user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
            user.setUsername(email);
        }

        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        userRepository.save(user);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
