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
public class TransferStrategy implements TransactionStrategy
{
    private TransactionRepo transactionRepo;
    private LedgerEntryRepo ledgerEntryRepo;
    private AccountBalanceService accountBalanceService;

    public TransferStrategy(TransactionRepo transactionRepo, LedgerEntryRepo ledgerEntryRepo, AccountBalanceService accountBalanceService) {
        this.transactionRepo = transactionRepo;
        this.ledgerEntryRepo = ledgerEntryRepo;
        this.accountBalanceService = accountBalanceService;
    }

    @Transactional
    @Override
    public Transaction process(Account fromAccount, Account toAccount, BigDecimal amount)
    {
        if (fromAccount == null || toAccount == null || amount == null) {
            throw new IllegalArgumentException("Accounts and amount must not be null");
        }

        if (fromAccount.getId() == toAccount.getId())
        {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("Amount must be positive");
        }

        BigDecimal balance = accountBalanceService.getBalance(fromAccount);
        if (balance.compareTo(amount) < 0){
            throw new IllegalArgumentException("Insufficient Funds");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCreatedDate(LocalDateTime.now());
        transaction = transactionRepo.save(transaction);

    try {
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setAccount(fromAccount);
        ledgerEntry.setTransaction(transaction);
        ledgerEntry.setAmount(amount.negate());
        ledgerEntry.setTimestamp(LocalDateTime.now());
        ledgerEntry.setDescription("Transfer to " + toAccount.getAccountNumber());
        ledgerEntry = ledgerEntryRepo.save(ledgerEntry);

        LedgerEntry ledgerEntry2 = new LedgerEntry();
        ledgerEntry2.setAccount(toAccount);
        ledgerEntry2.setTransaction(transaction);
        ledgerEntry2.setAmount(amount);
        ledgerEntry2.setTimestamp(LocalDateTime.now());
        ledgerEntry2.setDescription("Transfer from " + fromAccount.getAccountNumber());
        ledgerEntry2 = ledgerEntryRepo.save(ledgerEntry2);

        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction = transactionRepo.save(transaction);

        return transaction;
    }catch (Exception e)
    {
        transaction.setTransactionStatus(TransactionStatus.FAILED);
        transaction = transactionRepo.save(transaction);
        throw e;
    }
    }
}
