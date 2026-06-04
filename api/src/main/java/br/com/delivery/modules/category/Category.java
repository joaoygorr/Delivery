package br.com.delivery.modules.category;

import br.com.delivery.modules.crud.EntityBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_category"))
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Category extends EntityBase {

    @Column(nullable = false)
    private String name;
}
