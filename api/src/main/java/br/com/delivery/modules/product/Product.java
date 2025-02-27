package br.com.delivery.modules.product;

import br.com.delivery.modules.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "Products")
@Entity(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idProduct")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

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
