package com.example.FinPlanner.service;

import com.example.FinPlanner.model.Investments;
import com.example.FinPlanner.repository.InvestmentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentsService implements ServiceInterface<Investments>, SortInterface{
    @Autowired
    InvestmentsRepository investmentsRepository;
    @Override
    public void save(Investments investments) {
        investmentsRepository.save(investments);
    }

    @Override
    public Iterable<Investments> getAll() {
        return investmentsRepository.findAll();
    }

    @Override
    public void delete(Investments investments) {
        investmentsRepository.delete(investments);
    }
    //In the future add some logic to this method
    @Override
    public void update(Investments investments) {
        Investments old = investmentsRepository.findById(investments.getId()).orElseThrow(EntityNotFoundException::new);
        investmentsRepository.save(investments);
    }

    @Override
    public Investments getById(Long id) {
        return investmentsRepository.getInvestmentsById(id);
    }

    @Override
    public Sort sort(Sort.Direction direction, String properties) {
        return null;
    }

    public List<Investments> getAllByType(Investments.InvestmentsType type){
        return investmentsRepository.getAllByInvestmentsType(type);
    }
}
