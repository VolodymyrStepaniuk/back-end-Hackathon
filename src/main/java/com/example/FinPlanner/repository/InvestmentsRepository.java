package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.Investments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentsRepository extends JpaRepository<Investments, Long> {
    List<Investments> getAllByInvestmentsType(Investments.InvestmentsType type);
    Investments getInvestmentsById(Long id);
}
