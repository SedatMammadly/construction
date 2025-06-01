package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.construction.cache.RedisVerificationCodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisVerificationCodeService redisTokenService;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendVerificationCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String verificationCode = RandomStringUtils.randomNumeric(6);
        message.setFrom(from);
        message.setTo(email);
        message.setText(verificationCode);
        message.setSubject("Your Verification Code:");
        mailSender.send(message);
    }


}
