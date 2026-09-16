package com.Favuur.WalletSystem.dto;

import com.Favuur.WalletSystem.model.TransactionStatus;
import com.Favuur.WalletSystem.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionResponse
{
    private long transactionId;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private LocalDateTime createdDate;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", createdDate=" + createdDate +
                '}';
    }
}
