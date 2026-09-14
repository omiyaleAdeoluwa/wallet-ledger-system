package com.Favuur.WalletSystem.dto;

import java.math.BigDecimal;

public class TransferRequest
{
    private long fromAccountId;
    private long toAccountId;
    private BigDecimal amount;
    private String enteredPin;

    public long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEnteredPin() {
        return enteredPin;
    }

    public void setEnteredPin(String enteredPin) {
        this.enteredPin = enteredPin;
    }
}
