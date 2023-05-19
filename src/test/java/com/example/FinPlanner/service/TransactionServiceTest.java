package com.example.FinPlanner.service;

import com.example.FinPlanner.model.Card;
import com.example.FinPlanner.model.Transaction;
import com.example.FinPlanner.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Test
    void dbConnectTest() throws InterruptedException {
        Transaction transaction = new Transaction();
        transaction.setDescription("TEST1");
        transaction.setAmount(500.0);
        transaction.setTransactionType(Transaction.TransactionType.SPENDING);
        transactionService.save(transaction);
        Transaction transaction2 = new Transaction();
        transaction2.setDescription("TEST2");
        transaction2.setAmount(200.0);
        transaction2.setTransactionType(Transaction.TransactionType.SPENDING);
        transactionService.save(transaction2);
        List<Transaction> results = transactionService.findAllSortByDate();
        List<Transaction> resultsAmount = transactionService.findAllSortByAmountDesc();
        assertEquals(results.get(0).getDescription(), "TEST1");
        assertEquals(resultsAmount.get(0).getAmount(), 500.0);
    }

    @Test
    void testAddMultipleTransactionsToCard() throws InterruptedException {
        // Create a new Card object and set its properties
        Card card = new Card();
        card.setCardNum("111111111");
        card.setCardTransactions(new ArrayList<>());

        // Create multiple Transaction objects and set their properties
        Transaction transaction1 = new Transaction();
        transaction1.setDescription("TEST1");
        transaction1.setAmount(500.0);
        transaction1.setTransactionCard(card);
        transaction1.setTransactionType(Transaction.TransactionType.SPENDING);

        Transaction transaction2 = new Transaction();
        transaction2.setDescription("TEST2");
        transaction2.setAmount(300.0);
        transaction2.setTransactionCard(card);
        transaction2.setTransactionType(Transaction.TransactionType.SPENDING);

        // Add the Transaction objects to the Card object's cardTransactions list
        card.getCardTransactions().add(transaction1);
        sleep(100);
        card.getCardTransactions().add(transaction2);

        // Save the Card object using your CardRepository
        cardRepository.save(card);

        // Retrieve the Card object from the database using its ID
        Card savedCard = cardRepository.findById(card.getId()).orElse(null);

        // Verify that the number of transactions on the retrieved Card object matches the number of transactions you added
        assertEquals(savedCard.getCardTransactions().size(), 2);
    }


}
