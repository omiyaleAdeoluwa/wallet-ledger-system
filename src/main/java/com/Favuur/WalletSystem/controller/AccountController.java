package com.Favuur.WalletSystem.controller;

import com.Favuur.WalletSystem.model.Account;
import com.Favuur.WalletSystem.model.User;
import com.Favuur.WalletSystem.repository.AccountRepo;
import com.Favuur.WalletSystem.repository.UserRepo;
import com.Favuur.WalletSystem.service.AccountBalanceService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/account")
public class AccountController
{
    private UserRepo userRepo;
    private AccountRepo accountRepo;
    private AccountBalanceService accountBalanceService;

    public AccountController(UserRepo userRepo, AccountRepo accountRepo, AccountBalanceService accountBalanceService) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.accountBalanceService = accountBalanceService;
    }

    @PostMapping
    public Account createAccount(@RequestParam long userId)
    {
        User user = userRepo.findById(userId).orElseThrow(()-> new IllegalArgumentException("can't find user"));
        Account acc = new Account();
        acc.setUser(user);
        acc.setCreatedDate(LocalDateTime.now());
        acc = accountRepo.save(acc);

        acc.setAccountNumber("ACC" + String.format("%06d", acc.getId()));
        return accountRepo.save(acc);


    }

    @GetMapping("/{accountId}/balance")
    public BigDecimal getBalance(@PathVariable long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not Found"));
        return accountBalanceService.getBalance(account);
    }
}
