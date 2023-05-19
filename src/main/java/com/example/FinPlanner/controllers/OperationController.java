package com.example.FinPlanner.controllers;

import com.example.FinPlanner.model.Operation;
import com.example.FinPlanner.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/operations")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation){
        operationService.save(operation);
        return operation;
    }

    @GetMapping("/card/{card_number}")
    public Iterable<Operation> getAllOperationByNumberOfCard(@PathVariable("card_number") String numberOfCard){
        return operationService.getAllByCardNumber(numberOfCard);
    }

    @GetMapping
    public Iterable<Operation> getAllOperations(){
        return operationService.getAll();
    }

    @GetMapping("/{operation_id}")
    public Operation viewOperation(@PathVariable("operation_id") Long id){
        return operationService.getById(id);
    }

    @PutMapping("/{operation_id}/{active}")
    public Operation update(@PathVariable("operation_id") Long id, @PathVariable("active") boolean active){
        operationService.updateActive(id,active);
        return operationService.getById(id);
    }

    @GetMapping("/{operations_type}/all")
    public List<Operation> getOperationsByType(@PathVariable("operations_type") Operation.OperationType operationType){
        return operationService.getOperationByType(operationType);
    }

    @GetMapping("/{operations_type}/{active}")
    public List<Operation> getOperationsByType(@PathVariable("operations_type") Operation.OperationType operationType,
                                               @PathVariable("active") boolean isActive){
        return operationService.getOperationByType(operationType).stream().filter(x->x.isActive()==isActive).toList();
    }

    @DeleteMapping("/{operation_id}")
    public void deleteOperation(@PathVariable("operation_id") Long id){
        operationService.delete(operationService.getById(id));
    }
}
