package org.example.construction.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.construction.cache.RedisVerificationCodeService;
import org.example.construction.dto.AuthRequest;
import org.example.construction.dto.VerificationRequest;
import org.example.construction.enums.Role;
import org.example.construction.model.User;
import org.example.construction.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisVerificationCodeService verificationCodeService;

    public void register(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public String authenticate(AuthRequest authRequest, HttpSession session) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setMaxInactiveInterval(60 * 60 * 24 * 30);
        return "Login Successful";
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    public Boolean verify(VerificationRequest verificationRequest) {
        userRepository.findByUsername(verificationRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        return verificationCodeService.codeIsValid(verificationRequest.getVerificationCode());
    }
}
