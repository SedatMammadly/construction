package org.example.construction.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AuthRequest;
import org.example.construction.dto.ForgetPasswordDto;
import org.example.construction.dto.ResetPasswordRequest;
import org.example.construction.dto.VerificationRequest;
import org.example.construction.service.PasswordChangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordChangeService passwordChangeService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
       return ResponseEntity.ok(authenticationService.register(authRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest, HttpSession session) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest, session));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email, @RequestBody ForgetPasswordDto dto) {
        return ResponseEntity.ok(passwordChangeService.forgetPassword(email, dto));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(passwordChangeService.resetPassword(email, resetPasswordRequest));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestBody VerificationRequest verificationRequest) {
        return ResponseEntity.ok(authenticationService.verify(verificationRequest));
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}
