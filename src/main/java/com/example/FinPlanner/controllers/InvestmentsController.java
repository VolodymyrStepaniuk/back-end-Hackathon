package com.example.FinPlanner.controllers;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.Investments;
import com.example.FinPlanner.service.InvestmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/investments")
public class InvestmentsController {
    @Autowired
    InvestmentsService service;

    @PostMapping
    public Investments createInvestments(@RequestBody Investments investments){
        service.save(investments);
        return investments;
    }

    @GetMapping
    public Iterable<Investments> getAllInvestments(){
        return service.getAll();
    }

    @GetMapping("/{investments_type}")
    public List<Investments> getAllInvestmentsByType(@PathVariable("investments_type") Investments.InvestmentsType type){
        return service.getAllByType(type);
    }

    @GetMapping("/{investments_type}/all")
    public double getPriceOfAllInvestments(@PathVariable("investments_type") Investments.InvestmentsType type){
        return service.getAllByType(type)
                .stream().mapToDouble(x -> x.getPriceOfOneInvestments() * x.getCountOfInvestments())
                .sum();
    }

    @DeleteMapping("/{sell_id}")
    public ResponseEntity<String> sellInvestments(@PathVariable("sell_id") Long id){
        Investments investments = service.getById(id);
        Card card;
        try {
            card = investments.getOwner()
                    .getCardList()
                    .get(0);
        }catch (IndexOutOfBoundsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: 'card' is required");
        }
        card.setCurrentBalance(card.getCurrentBalance()
                + investments.getPriceOfOneInvestments()
                * investments.getCountOfInvestments());
        service.delete(investments);
        return ResponseEntity.ok("Request processed successfully");

    }
}
