package com.example.FinPlanner.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    //The field is not used
    private String email;
    //The field is not used
    private String password;

    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Card> cardList;

    public double getTotalIncome(){
        double result = 0.0;
        for (Card card : cardList)
            result += card.getCurrentBalance();
        return result;
    }

}
