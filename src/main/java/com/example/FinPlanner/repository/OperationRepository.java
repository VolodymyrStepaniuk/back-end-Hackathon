package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findById(Long id);

    List<Operation> findAllByOperationCard(Card card);

    List<Operation> findAllByType(Operation.OperationType type);
}
