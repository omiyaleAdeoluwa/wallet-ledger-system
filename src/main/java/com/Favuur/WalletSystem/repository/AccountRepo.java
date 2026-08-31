package com.Favuur.WalletSystem.repository;

import com.Favuur.WalletSystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long>
{

}
