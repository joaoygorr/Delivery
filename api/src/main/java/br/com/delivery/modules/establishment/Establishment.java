package br.com.delivery.modules.establishment;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "Establishments")
@Entity(name = "Establishments")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEstablishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstablishment;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;
}
