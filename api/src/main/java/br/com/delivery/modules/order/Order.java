package br.com.delivery.modules.order;

import br.com.delivery.modules.crud.EntityBase;
import br.com.delivery.modules.establishment.Establishment;
import br.com.delivery.modules.order.enuns.OrderStatus;
import br.com.delivery.modules.order.enuns.PaymentType;
import br.com.delivery.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "orders")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_order"))
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Order extends EntityBase {

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
