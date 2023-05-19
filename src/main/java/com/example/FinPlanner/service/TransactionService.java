package com.example.FinPlanner.service;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.Transaction;
import com.example.FinPlanner.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ServiceInterface<Transaction>,SortInterface{

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
    //In the future add some logic to this method
    @Override
    public void update(Transaction transaction) {
        Transaction old = transactionRepository.findById(transaction.getId()).orElseThrow(EntityNotFoundException::new);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByType(Transaction.TransactionType type){
        return transactionRepository.getTransactionByTransactionType(type);
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Transaction> findAllSortByDate(){
        return transactionRepository.findAll(sort(Sort.Direction.ASC,"transactionTime"));
    }

    public List<Transaction> findAllSortByDateDesc(){
        return transactionRepository.findAll(sort(Sort.Direction.DESC,"transactionTime"));
    }

    public List<Transaction> findAllSortByAmount(){
        return transactionRepository.findAll(sort(Sort.Direction.ASC, "amount"));
    }

    public List<Transaction> findAllSortByAmountDesc(){
        return transactionRepository.findAll(sort(Sort.Direction.DESC, "amount"));
    }


    public List<Transaction> findByCardSortByDateDesc(Card card){
        return transactionRepository.getTransactionsByTransactionCard(card, sort(Sort.Direction.DESC, "transactionTime"));
    }

    public List<Transaction> findByCardSortByDateAsc(Card card){
        return transactionRepository.getTransactionsByTransactionCard(card, sort(Sort.Direction.ASC, "transactionTime"));
    }

    public Double getAmountFromCurrentMonth(Transaction.TransactionType type){
        return transactionRepository.getTransactionsForCurrentMonthByType(type);
    }

    @Override
    public Sort sort(Sort.Direction direction, String properties) {
        return Sort.by(direction, properties);
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
