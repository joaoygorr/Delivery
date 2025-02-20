package br.com.delivery.modules.order;

import br.com.delivery.modules.establishment.Establishment;
import br.com.delivery.modules.order.enuns.OrderStatus;
import br.com.delivery.modules.order.enuns.PaymentType;
import br.com.delivery.modules.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "Orders")
@Entity(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private Establishment establishment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentMethod;

    private BigDecimal changeAmount;

    @Column(nullable = false)
    private BigDecimal deliveryPrice;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
}
