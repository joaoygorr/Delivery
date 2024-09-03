package com.br.burguer.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private Long idSize;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(name = "max_combination")
    private Integer maxCombination;
}
