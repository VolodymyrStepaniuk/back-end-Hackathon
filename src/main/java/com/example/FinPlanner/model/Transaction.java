package com.example.FinPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "card_transactions",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Card transactionCard;

    private String description;

    private Double amount;

    @Column(name = "card_balance")
    private Double currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Getter
    public enum TransactionType {
        INCOME("Income"), SPENDING("Spending");

        private final String type;

        TransactionType(String type) {
            this.type = type;
        }

        }

    public Transaction() {
        this.transactionTime = LocalDateTime.now();
    }
}
