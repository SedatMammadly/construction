package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ForgetPasswordDto;
import org.example.construction.dto.ResetPasswordRequest;
import org.example.construction.model.User;
import org.example.construction.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String forgetPassword(String email, ForgetPasswordDto dto) {
        User user = userRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("User not found"));
        passwordCheck(user, dto.getNewPassword(), dto.getConfirmPassword());
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
        return "Password changed successfully";
    }

    public String resetPassword(String email, ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(user.getPassword(), resetPasswordRequest.getOldPassword())) {
            throw new RuntimeException("Password does not match old password ");
        }
        passwordCheck(user, resetPasswordRequest.getNewPassword(), resetPasswordRequest.getConfirmPassword());
        userRepository.save(user);
        return "Password reset successfully";
    }

    private void passwordCheck(User user, String confirmPassword, String newPassword) {
        if (passwordEncoder.matches(user.getPassword(), newPassword)) {
            throw new RuntimeException("Password is same with current password ");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Password does not match confirm password ");
        }
    }

}
