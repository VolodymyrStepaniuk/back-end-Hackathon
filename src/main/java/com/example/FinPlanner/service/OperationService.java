package com.example.FinPlanner.service;

import com.example.FinPlanner.model.Operation;
import com.example.FinPlanner.repository.OperationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService implements ServiceInterface<Operation>, SortInterface{

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    CardService cardService;

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public Iterable<Operation> getAll() {
        return operationRepository.findAll();
    }

    public Iterable<Operation> getAllByPercent() {
        return operationRepository.findAll(sort(Sort.Direction.ASC, "percent"));
    }

    public Iterable<Operation> getAllByPercentDesc() {
        return operationRepository.findAll(sort(Sort.Direction.DESC, "percent"));
    }


    @Override
    public void delete(Operation operation) {
        operationRepository.delete(operation);
    }
    //In the future add some logic to this method
    @Override
    public void update(Operation operation) {
//        Operation old = operationRepository.findById(operation.getId()).orElseThrow(EntityNotFoundException::new);
        operationRepository.save(operation);
    }
    public void updateActive(Long id,boolean active){
        Operation operation = operationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        operation.setActive(active);
        operationRepository.save(operation);
    }

    @Override
    public Sort sort(Sort.Direction direction, String properties) {
        return Sort.by(direction, properties);
    }
    
    @Override
    public Operation getById(Long Id){
        return operationRepository.findById(Id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Operation> getOperationByType(Operation.OperationType type){
        return operationRepository.findAllByType(type);
    }

    public Iterable<Operation> getAllByCardNumber(String cardNumber){
        return operationRepository.findAllByOperationCard(cardService.getByNum(cardNumber));
    }
}
