package br.com.delivery.modules.order.dtos;

import br.com.delivery.modules.crud.DtoBase;
import br.com.delivery.modules.order.enuns.OrderStatus;
import br.com.delivery.modules.order.enuns.PaymentType;
import br.com.delivery.validators.interfaces.ValueEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends DtoBase {

    @NotNull(message = "Usuário não pode estar em branco.")
    private Long user;

    @NotNull(message = "Estabelecimento não pode estar em branco.")
    private Long establishment;

    @ValueEnum(enumClass = PaymentType.class, message = "Deve ser informado um tipo de pagamento válido.")
    private String paymentMethod;

    private BigDecimal changeAmount;

    @NotNull(message = "Preço de entrega não pode estar em branco.")
    private BigDecimal deliveryPrice;

    @NotNull(message = "SubTotal não pode estar em branco.")
    private BigDecimal subtotal;

    @NotNull(message = "Data do pedido não pode estar em branco.")
    private LocalDateTime orderDate;

    @ValueEnum(enumClass = OrderStatus.class, message = "Deve ser informado uma status válido.")
    private String status;
}
