package com.Favuur.WalletSystem.service;

import com.Favuur.WalletSystem.model.Account;
import com.Favuur.WalletSystem.repository.LedgerEntryRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountBalanceService
{
    private LedgerEntryRepo ledgerEntryRepo;

    public AccountBalanceService(LedgerEntryRepo ledgerEntryRepo)
    {
        this.ledgerEntryRepo = ledgerEntryRepo;
    }

    public BigDecimal getBalance(Account account)
    {
        BigDecimal balance = ledgerEntryRepo.getBalanceByAccount(account);
        return balance != null ? balance : BigDecimal.ZERO;
    }
}

