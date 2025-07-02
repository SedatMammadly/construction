package org.example.construction.authentication;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.construction.dto.*;
import org.example.construction.model.User;
import org.example.construction.repository.UserRepository;
import org.example.construction.service.PasswordChangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordChangeService passwordChangeService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationService.register(authRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody LogoutRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email, @RequestBody ForgetPasswordDto dto) {
        return ResponseEntity.ok(passwordChangeService.forgetPassword(email, dto));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(passwordChangeService.resetPassword(userDetails, resetPasswordRequest));
    }

    @PutMapping("/reset/email")
    public ResponseEntity<AuthResponse> resetEmail(@RequestParam(name = "param") String email, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(authenticationService.resetEmail(email, userDetails));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestBody VerificationRequest verificationRequest) {
        return ResponseEntity.ok(authenticationService.verify(verificationRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshAuthToken(HttpServletRequest request) throws IOException {
        AuthResponse authResponse = authenticationService.refreshAuthToken(request);
        return ResponseEntity.ok(authResponse);
    }

}
