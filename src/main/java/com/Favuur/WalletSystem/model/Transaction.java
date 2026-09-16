package com.Favuur.WalletSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private LocalDateTime createdDate;


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getId() {
        return id;
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
        createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", CreatedDate=" + createdDate +
                '}';
    }
}
