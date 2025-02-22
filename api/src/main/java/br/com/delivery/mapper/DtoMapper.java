package br.com.delivery.mapper;

import org.mapstruct.MappingTarget;

public interface DtoMapper<E, D> {

    E toEntity(D dto, @MappingTarget E entity);
}
