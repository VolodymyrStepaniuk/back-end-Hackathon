package com.example.FinPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "card_operations",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Card operationCard;

    @Column(name = "bank_name")
    private String bankName;

    private Double amount;

    private Double percent;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Getter
    public enum OperationType {
        DEPOSIT("Deposit"), CREDIT("Credit");

        private final String type;

        OperationType(String type) {
            this.type = type;
        }

    }
    public Operation() {
        this.paymentDate = LocalDateTime.now();
    }
}
