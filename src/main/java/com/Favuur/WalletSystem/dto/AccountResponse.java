package com.Favuur.WalletSystem.dto;

import java.time.LocalDateTime;

public class AccountResponse
{
    private long userId;
    private long id;
    private String accountNumber;
    private LocalDateTime createdDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "userId=" + userId +
                ", id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
