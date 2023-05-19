package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long > {
    Optional<Transaction> getTransactionById(Long id);

    List<Transaction> getTransactionsByTransactionCard(Card card, Sort sort);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE MONTH(t.transactionTime) = MONTH(CURRENT_DATE) AND YEAR(t.transactionTime) = YEAR(CURRENT_DATE) AND t.transactionType = :type")
    Double getTransactionsForCurrentMonthByType(@Param("type") Transaction.TransactionType type);
}
