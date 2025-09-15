package br.com.delivery.modules.order;

import br.com.delivery.modules.crud.CrudController;
import br.com.delivery.modules.order.dtos.OrderDTO;
import br.com.delivery.modules.order.mapper.OrderMapper;
import br.com.delivery.modules.order.specifications.OrderSpec;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Order", description = "Endpoint related to order")
public class OrderController extends CrudController<Order, OrderRepository, OrderSpec, OrderDTO, OrderMapper> {
}
