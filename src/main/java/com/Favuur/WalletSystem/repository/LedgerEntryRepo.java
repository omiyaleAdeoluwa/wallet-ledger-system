package com.Favuur.WalletSystem.repository;

import com.Favuur.WalletSystem.model.Account;
import com.Favuur.WalletSystem.model.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LedgerEntryRepo extends JpaRepository<LedgerEntry, Long>
{
    @Query("SELECT SUM(l.amount) FROM LedgerEntry l WHERE l.account = :account")
    BigDecimal getBalanceByAccount(@Param("account") Account account);
}
