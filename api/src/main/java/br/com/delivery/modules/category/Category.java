package br.com.delivery.modules.category;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "Categories")
@Entity(name = "Categories")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(nullable = false)
    private String name;
}
