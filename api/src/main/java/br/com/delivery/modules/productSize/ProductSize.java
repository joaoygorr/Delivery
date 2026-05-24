package br.com.delivery.modules.productSize;

import br.com.delivery.modules.categoryComplementary.CategoryComplementary;
import br.com.delivery.modules.crud.EntityBase;
import br.com.delivery.modules.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_product_size"))
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ProductSize extends EntityBase {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "integer default 0")
    private Integer maxCombination = 1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany
    @JoinTable(name = "product_size_category_complementary", joinColumns = @JoinColumn(name = "product_size_id"),
            inverseJoinColumns = @JoinColumn(name = "category_complementary_id"))
    private List<CategoryComplementary> complementCategories;
}
