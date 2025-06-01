package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.config.FileConfig;
import org.example.construction.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService service;

    @PostMapping("/sendVerificationCode")
    public void sendVerificationCode(@RequestParam("email") String email) {
        service.sendVerificationCode(email);
    }
}
