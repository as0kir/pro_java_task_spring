package ru.askir.spring.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.askir.spring.product.dto.TypeAccount;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String account;
    private BigDecimal balance;
    private TypeAccount typeAccount;
}
