package com.Favuur.WalletSystem.controller;

import com.Favuur.WalletSystem.dto.TransactionResponse;
import com.Favuur.WalletSystem.dto.TransferRequest;
import com.Favuur.WalletSystem.dto.WithdrawRequest;
import com.Favuur.WalletSystem.model.Transaction;
import com.Favuur.WalletSystem.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public TransactionResponse deposit(@RequestParam long toAccountId, @RequestParam BigDecimal amount){
        Transaction transaction = transactionService.deposit(toAccountId, amount);

        TransactionResponse response = new TransactionResponse();
        response.setTransactionType(transaction.getTransactionType());
        response.setTransactionStatus(transaction.getTransactionStatus());
        response.setCreatedDate(transaction.getCreatedDate());
        response.setTransactionId(transaction.getId());

        return response;

    }

    @PostMapping("/withdraw")
    public TransactionResponse withdraw(@RequestBody WithdrawRequest request)
    {
        Transaction transaction = transactionService.withdraw(request.getFromAccountId(), request.getAmount(), request.getEnteredPin());

        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(transaction.getId());
        response.setTransactionStatus(transaction.getTransactionStatus());
        response.setTransactionType(transaction.getTransactionType());
        response.setCreatedDate(transaction.getCreatedDate());

        return response;
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(@RequestBody TransferRequest request)
    {
        Transaction transaction = transactionService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount(), request.getEnteredPin());

        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(transaction.getId());
        response.setTransactionType(transaction.getTransactionType());
        response.setTransactionStatus(transaction.getTransactionStatus());
        response.setCreatedDate(transaction.getCreatedDate());
        return response;
    }
}
