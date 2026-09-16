package com.Favuur.WalletSystem.strategy;

import com.Favuur.WalletSystem.model.Account;
import com.Favuur.WalletSystem.model.Transaction;

import java.math.BigDecimal;

public interface TransactionStrategy
{
    Transaction process(Account fromAccount, Account toAccount, BigDecimal amount);
}
