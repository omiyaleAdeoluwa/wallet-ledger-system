package com.Favuur.WalletSystem.service;

import com.Favuur.WalletSystem.model.Account;
import com.Favuur.WalletSystem.model.Transaction;
import com.Favuur.WalletSystem.model.TransactionType;
import com.Favuur.WalletSystem.repository.AccountRepo;
import com.Favuur.WalletSystem.strategy.TransactionStrategy;
import com.Favuur.WalletSystem.strategy.TransactionStrategyFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class TransactionService
{
    private AccountRepo accountRepo;
    private TransactionStrategyFactory factory;
    private PinVerification pinVerify;

    public TransactionService(AccountRepo accountRepo, TransactionStrategyFactory factory, PinVerification pinVerify) {
        this.accountRepo = accountRepo;
        this.factory = factory;
        this.pinVerify = pinVerify;
    }

    public Transaction deposit(long toAccountId, BigDecimal amount)
    {
        Account toAccount = accountRepo.findById(toAccountId).orElseThrow(() -> new IllegalArgumentException("Äccount not Found"));
        TransactionStrategy strategy = factory.getStrategy(TransactionType.DEPOSIT);
        return strategy.process(null, toAccount, amount);
    }

    public Transaction withdraw(long fromAccountId, BigDecimal amount, String enteredPin)
    {
        Account fromAccount = accountRepo.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("Account not Found"));
        if(!pinVerify.verifyPin(fromAccount.getUser(), enteredPin))
        {throw new IllegalArgumentException("Incorrect Pin");}

        TransactionStrategy strategy = factory.getStrategy(TransactionType.WITHDRAW);
        return strategy.process(fromAccount, null, amount);
    }

    public Transaction transfer(long fromAccountId, long toAccountId, BigDecimal amount, String enteredPin)
    {
        Account toAccount = accountRepo.findById(toAccountId).orElseThrow(()-> new IllegalArgumentException("Account not Found"));
        Account fromAccount = accountRepo.findById(fromAccountId).orElseThrow(()-> new IllegalArgumentException("Account not Found"));
        if(!pinVerify.verifyPin(fromAccount.getUser(), enteredPin))
        {throw new IllegalArgumentException("Incorrect Pin");}

        TransactionStrategy strategy = factory.getStrategy(TransactionType.TRANSFER);
        return strategy.process(fromAccount, toAccount, amount);
    }
}
