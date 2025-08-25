package br.com.delivery.modules.establishment.mapper;

import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.establishment.Establishment;
import br.com.delivery.modules.establishment.dtos.EstablishmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class EstablishmentMapper implements DtoMapper<Establishment, EstablishmentDTO> {
}
