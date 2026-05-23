package br.com.delivery.modules.additional;


import br.com.delivery.modules.categoryComplementary.CategoryComplementary;
import br.com.delivery.modules.crud.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_additional"))
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Additional extends EntityBase {

    @ManyToOne
    @JoinColumn(name = "id_category_complementary", nullable = false)
    private CategoryComplementary categoryComplementary;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
