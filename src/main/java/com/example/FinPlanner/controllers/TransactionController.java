package com.example.FinPlanner.controllers;

import com.example.FinPlanner.model.Transaction;
import com.example.FinPlanner.service.CardService;
import com.example.FinPlanner.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    CardService cardService;

    @GetMapping
    public List<Transaction> getAll(){
        return transactionService.getAll();
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction){
        transactionService.save(transaction);
        return transaction;
    }

    @GetMapping("/card/{card_id}")
    public List<Transaction> getByCard(@PathVariable("card_id") Long cardId){
        return cardService.getById(cardId).getCardTransactions();
    }

    @GetMapping("/card/{card_id}/desc")
    public List<Transaction> getByCardSorted(@PathVariable("card_id") Long cardId){
        return transactionService.findByCardSortByDateDesc(cardService.getById(cardId));
    }

    @GetMapping("/card/{card_id}/asc")
    public List<Transaction> getByCardSortedAsc(@PathVariable("card_id") Long cardId){
        return transactionService.findByCardSortByDateAsc(cardService.getById(cardId));
    }

    @PutMapping("/{transaction_id}")
    public Transaction update(@PathVariable("transaction_id") Long id, @RequestBody Transaction transaction){
        transactionService.update(transaction);
        return transactionService.findById(id);
    }
    @GetMapping("/{transaction_type}")
    public Double getAmountForCurrMonth(@PathVariable("transaction_type")Transaction.TransactionType type){
        return transactionService.getAmountFromCurrentMonth(type);
    }

    @DeleteMapping("/{transaction_id}")
    public void delete(@PathVariable("transaction_id") Long id){
        transactionService.delete(transactionService.getById(id));
    }
}
