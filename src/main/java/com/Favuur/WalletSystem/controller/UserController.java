package com.Favuur.WalletSystem.controller;

import com.Favuur.WalletSystem.dto.CreateUserRequest;
import com.Favuur.WalletSystem.dto.UserResponse;
import com.Favuur.WalletSystem.model.User;
import com.Favuur.WalletSystem.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request)
    {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setHashedPin(passwordEncoder.encode(request.getPin()));

        user = userRepo.save(user);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;

    }
}
