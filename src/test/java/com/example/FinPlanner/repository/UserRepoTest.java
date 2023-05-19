package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.User;
import org.hibernate.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Test
    void dbConnectTest(){
        User user = new User();
        Card card = new Card();
        List<Card> list = new ArrayList<>();
        list.add(card);
        user.setFirstName("Bob");
        user.setLastName("Bob");
        user.setEmail("123");
        user.setPassword("123");
        user.setCardList(list);
        card.setCardNum("1234567812345678");
        card.setCurrentBalance(100.0);
        card.setOwner(user);
        user.getCardList().add(card);
        userRepository.save(user);
        assertEquals(userRepository.findAll().size(),2);
    }
}
