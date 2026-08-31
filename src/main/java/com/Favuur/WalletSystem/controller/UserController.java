package com.Favuur.WalletSystem.controller;

import com.Favuur.WalletSystem.model.User;
import com.Favuur.WalletSystem.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public User createUser(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String pin )
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPin(passwordEncoder.encode(pin));

        return userRepo.save(user);
    }
}
