package com.Favuur.WalletSystem.controller;

import com.Favuur.WalletSystem.model.Transaction;
import com.Favuur.WalletSystem.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam long toAccountId, @RequestParam BigDecimal amount){
        return transactionService.deposit(toAccountId, amount);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam long fromAccountId, @RequestParam BigDecimal amount, @RequestParam String enteredPin)
    {
        return transactionService.withdraw(fromAccountId, amount, enteredPin);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam long fromAccountId, @RequestParam long toAccountId, @RequestParam BigDecimal amount, @RequestParam String enteredPin )
    {
        return transactionService.transfer(fromAccountId, toAccountId, amount, enteredPin);
    }
}
