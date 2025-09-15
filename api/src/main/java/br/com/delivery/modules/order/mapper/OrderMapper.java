package br.com.delivery.modules.order.mapper;

import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.order.Order;
import br.com.delivery.modules.order.dtos.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class OrderMapper implements DtoMapper<Order, OrderDTO> {
}
