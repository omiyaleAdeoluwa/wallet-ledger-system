package com.Favuur.WalletSystem.service;

import com.Favuur.WalletSystem.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PinVerification
{
    private PasswordEncoder passwordEncoder;

    public PinVerification(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean verifyPin(User user, String enteredPin)
    {
        return passwordEncoder.matches(enteredPin, user.getHashedPin());
    }
}
