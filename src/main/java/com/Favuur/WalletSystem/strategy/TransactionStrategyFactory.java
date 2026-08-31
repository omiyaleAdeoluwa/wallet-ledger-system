package com.Favuur.WalletSystem.strategy;

import com.Favuur.WalletSystem.model.TransactionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionStrategyFactory
{
    private Map<TransactionType, TransactionStrategy> strategies;

    public TransactionStrategyFactory(DepositStrategy depositStrategy, WithdrawStrategy withdrawStrategy, TransferStrategy transferStrategy)
    {
        strategies = new HashMap<>();
        strategies.put(TransactionType.DEPOSIT, depositStrategy);
        strategies.put(TransactionType.WITHDRAW, withdrawStrategy);
        strategies.put(TransactionType.TRANSFER, transferStrategy);
    }

    public TransactionStrategy getStrategy(TransactionType transactionType)
    {
        return strategies.get(transactionType);
    }
}
