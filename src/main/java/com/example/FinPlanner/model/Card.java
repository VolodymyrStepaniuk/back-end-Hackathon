package com.example.FinPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNum;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "user_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User owner;

    @Column(name = "current_balance")
    private double currentBalance;
    @Column(name = "expiry_date")
    private String expiryDate;

    @OneToMany(mappedBy = "transactionCard", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> cardTransactions;

    @OneToMany(mappedBy = "operationCard", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> cardOperations;
}
