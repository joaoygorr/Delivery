package br.com.delivery.modules.product;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.crud.EntityBase;
import br.com.delivery.modules.crud.Image;
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

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Boolean complementary = false;

    @Embedded
    private Image image;
}
