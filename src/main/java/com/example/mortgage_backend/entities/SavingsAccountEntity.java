package com.example.mortgage_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "savingsAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long idLoan;

    private int withdrawal;
    private int deposit;
    private int salary;
    private int monthsAgo;  // 1:12
}
