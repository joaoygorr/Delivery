package br.com.delivery.modules.order.mapper;

import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.establishment.Establishment;
import br.com.delivery.modules.establishment.EstablishmentRepository;
import br.com.delivery.modules.order.Order;
import br.com.delivery.modules.order.dtos.OrderDTO;
import br.com.delivery.modules.user.User;
import br.com.delivery.modules.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class OrderMapper implements DtoMapper<Order, OrderDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    User mapUser(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new Exception404(String.format("Usuário com ID %d não encontrado", id)));
    }

    Establishment mapEstablishment(Long id) {
        return this.establishmentRepository.findById(id)
                .orElseThrow(() -> new Exception404(String.format("Estabelecimento com ID %d não encontrado", id)));
    }
}
