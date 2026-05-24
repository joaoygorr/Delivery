package br.com.delivery.modules.categoryComplementary;

import br.com.delivery.modules.categoryComplementary.enums.ComplementType;
import br.com.delivery.modules.crud.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_category_complementary"))
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class CategoryComplementary extends EntityBase {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplementType type;

    @Column(columnDefinition = "integer default 0")
    private Integer min = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer max = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer maxFree = 0;
}
