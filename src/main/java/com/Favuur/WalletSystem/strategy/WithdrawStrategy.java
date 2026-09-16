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
public class WithdrawStrategy implements TransactionStrategy
{
    private TransactionRepo transactionRepo;
    private LedgerEntryRepo ledgerEntryRepo;
    private AccountBalanceService accountBalanceService;

    public WithdrawStrategy(TransactionRepo transactionRepo, LedgerEntryRepo ledgerEntryRepo, AccountBalanceService accountBalanceService)
    {
        this.transactionRepo = transactionRepo;
        this.ledgerEntryRepo = ledgerEntryRepo;
        this.accountBalanceService = accountBalanceService;
    }
    @Transactional
    @Override
    public Transaction process(Account fromAccount, Account toAccount, BigDecimal amount) {
        if (fromAccount == null ||amount == null) {
            throw new IllegalArgumentException("Account and amount must not be null");
        }

        if(amount.compareTo(BigDecimal.ZERO) <= 0)
       {
           throw new IllegalArgumentException ("Amount must be greater than 0");
       }

       BigDecimal balance = accountBalanceService.getBalance(fromAccount);
       if(balance.compareTo(amount) < 0)
       {
           throw new IllegalArgumentException("Insufficient Balance");
       }

       Transaction transaction = new Transaction();
       transaction.setTransactionType(TransactionType.WITHDRAW);
       transaction.setTransactionStatus(TransactionStatus.PENDING);
       transaction.setCreatedDate(LocalDateTime.now());
       transaction = transactionRepo.save(transaction);

       try
       {
           LedgerEntry ledgerEntry = new LedgerEntry();
           ledgerEntry.setTransaction(transaction);
           ledgerEntry.setAccount(fromAccount);
           ledgerEntry.setAmount(amount.negate());
           ledgerEntry.setTimestamp(LocalDateTime.now());
           ledgerEntry.setDescription("Withdrawal of "+amount+" from " + fromAccount.getAccountNumber());
           ledgerEntry = ledgerEntryRepo.save(ledgerEntry);

           transaction.setTransactionStatus(TransactionStatus.COMPLETED);
           transaction = transactionRepo.save(transaction);

           return transaction;
       } catch (Exception e)
       {
           transaction.setTransactionStatus(TransactionStatus.FAILED);
           transaction = transactionRepo.save(transaction);
           throw e;
       }






    }


}
