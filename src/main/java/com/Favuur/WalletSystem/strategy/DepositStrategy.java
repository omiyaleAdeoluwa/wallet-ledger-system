package com.Favuur.WalletSystem.strategy;

import com.Favuur.WalletSystem.model.*;
import com.Favuur.WalletSystem.repository.LedgerEntryRepo;
import com.Favuur.WalletSystem.repository.TransactionRepo;
import com.Favuur.WalletSystem.service.AccountBalanceService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DepositStrategy implements TransactionStrategy
{   private TransactionRepo transactionRepo;
    private LedgerEntryRepo ledgerEntryRepo;
    private AccountBalanceService accountBalanceService;

    public DepositStrategy(TransactionRepo transactionRepo, LedgerEntryRepo ledgerEntryRepo, AccountBalanceService accountBalanceService)
    {
        this.transactionRepo = transactionRepo;
        this.ledgerEntryRepo = ledgerEntryRepo;
        this.accountBalanceService = accountBalanceService;
    }
    @Transactional
    @Override
    public Transaction process(Account fromAccount, Account toAccount, BigDecimal amount) {
        if(toAccount == null || amount == null)
        {
            throw new IllegalArgumentException("Account and Amount can't be null");
        }
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCreatedDate(LocalDateTime.now());
        transaction = transactionRepo.save(transaction);

        try
        {
            LedgerEntry ledgerEntry = new LedgerEntry();
            ledgerEntry.setTransaction(transaction);
            ledgerEntry.setAccount(toAccount);
            ledgerEntry.setAmount(amount);
            ledgerEntry.setTimestamp(LocalDateTime.now());
            ledgerEntry.setDescription("Deposit of "+amount+" to " + toAccount.getAccountNumber());
            ledgerEntry = ledgerEntryRepo.save(ledgerEntry);

            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            transaction = transactionRepo.save(transaction);

            return transaction;

        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepo.save(transaction);
            throw e;
        }

    }
}
