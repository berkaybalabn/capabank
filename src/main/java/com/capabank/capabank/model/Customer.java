package com.capabank.capabank.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tcNo;
    private String firstName;
    private String lastName;
}
