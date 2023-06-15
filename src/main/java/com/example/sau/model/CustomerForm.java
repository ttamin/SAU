package com.example.sau.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "customer_form")
public class CustomerForm {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User user;
    private String name;
    private String city;
    private String address;
    private String phone;
    private String email;
    @Column(name = "payment_method")
    private String paymentMethod;
}
