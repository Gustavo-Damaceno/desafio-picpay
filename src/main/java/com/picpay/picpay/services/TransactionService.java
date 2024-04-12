package com.picpay.picpay.services;

import com.picpay.picpay.domain.transaction.Transaction;
import com.picpay.picpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    public void createTransaction(TransactionDTO transactionDTO) {

    }
}
