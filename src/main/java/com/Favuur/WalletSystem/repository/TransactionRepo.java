package com.Favuur.WalletSystem.repository;

import com.Favuur.WalletSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long>
{

}
