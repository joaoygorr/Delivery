package com.br.burguer.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complementary {

    @Id
    @Column(name = "id_complementary")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComplementary;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String name;

    private Integer quantity;

}
