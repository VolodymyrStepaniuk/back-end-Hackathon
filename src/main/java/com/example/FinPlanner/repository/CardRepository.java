package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card getCardByCardNum(String cardNUm);

    Optional<Card> getCardById(Long id);
}
