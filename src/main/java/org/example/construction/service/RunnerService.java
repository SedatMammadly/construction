package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.authentication.AuthenticationService;
import org.example.construction.dto.AuthRequest;
import org.example.construction.model.User;
import org.example.construction.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RunnerService implements CommandLineRunner {
    private final AuthenticationService authenticationService;
private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        List<User> userList=userRepository.findAll();
        if (userList.isEmpty()){
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername("serxanbabayev614@gmail.com");
            authRequest.setPassword("Serxan3232");
            System.out.println(authRequest.getUsername() + " " + authRequest.getPassword());
            authenticationService.register(authRequest);
        }

    }




}
