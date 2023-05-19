package com.example.FinPlanner.service;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ServiceInterface<Card>,SortInterface{
    @Autowired
    CardRepository cardRepository;

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public Card getById(Long id){
        return cardRepository.getCardById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Iterable<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card getByNum(String num){
        return cardRepository.getCardByCardNum(num);
    }

    @Override
    public void delete(Card card) {
        cardRepository.delete(card);
    }
    //In the future add some logic to this method
    @Override
    public void update(Card card) {
//        Card newCard = cardRepository.findById(card.getId()).orElseThrow();
//        newCard.setCurrentBalance(card.getCurrentBalance());
        cardRepository.save(card);
    }

    @Override
    public Sort sort(Sort.Direction direction, String properties) {
        return null;
    }
}
