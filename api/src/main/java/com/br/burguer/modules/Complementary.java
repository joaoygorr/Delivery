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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_complementary")
    private Long idComplementary;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    private BigDecimal price;

    private String name;

    private Integer quantity;

}
