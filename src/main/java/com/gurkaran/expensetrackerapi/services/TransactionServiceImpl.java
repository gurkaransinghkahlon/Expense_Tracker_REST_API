package com.gurkaran.expensetrackerapi.services;

import com.gurkaran.expensetrackerapi.domain.Transaction;
import com.gurkaran.expensetrackerapi.exceptions.EtBadRequestException;
import com.gurkaran.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.gurkaran.expensetrackerapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements  TransactionService{

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
        return transactionRepository.findAll(userId, categoryId);
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long TransactionDate) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId, categoryId, amount, note, TransactionDate);
        return transactionRepository.findById(userId, categoryId,transactionId);

    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        transactionRepository.update(userId, categoryId, transactionId, transaction);
    }

    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        transactionRepository.removeById(userId, categoryId, transactionId);
    }
}
