package br.com.delivery.modules.product;

import br.com.delivery.modules.category.Category;
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
@AttributeOverride(name = "id", column = @Column(name = "id_product"))
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends EntityBase {

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] img;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;
}
