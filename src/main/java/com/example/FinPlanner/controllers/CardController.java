package com.example.FinPlanner.controllers;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cards")
public class CardController {
    @Autowired
    private CardService service;

    @GetMapping
    public Iterable<Card> getAllCards(){
        return service.getAll();
    }

    @PostMapping
    public Card createCard(@RequestBody Card card){
        service.save(card);
        return card;
    }

    @GetMapping("/{card_id}")
    public Card viewCard(@PathVariable("card_id") Long id){
        return service.getById(id);
    }

    @PutMapping("/{card_id}")
    public Card updateCard(@PathVariable("card_id") Long id, @RequestBody Card card){
        service.update(card);
        return service.getById(id);
    }

    @DeleteMapping("/{card_id}")
    public void deleteCard(@PathVariable("card_id") Long id){
        service.delete(service.getById(id));
    }
}
