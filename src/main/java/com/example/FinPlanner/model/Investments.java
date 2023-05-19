package com.example.FinPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "investments")
@NoArgsConstructor
public class Investments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count_investments")
    private double countOfInvestments;
    @Column(name = "price_investments")
    private double priceOfOneInvestments;
    @Column(name = "investments_name")
    private String investmentsName;
    @Column(name="investments_date")
    private LocalDateTime investmentsDate;
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "user_investments",
            joinColumns = @JoinColumn(name = "investments_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User owner;

    @Column(name = "investments_type")
    @Enumerated(EnumType.STRING)
    private InvestmentsType investmentsType;

    @Getter
    public enum InvestmentsType{
        CRYPTO("Crypto"), COMPANYSHARES("SharesOfCompany");

        private final String type;

        InvestmentsType(String type) {
            this.type = type;
        }

    }
}
