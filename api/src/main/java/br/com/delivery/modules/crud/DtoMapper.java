package br.com.delivery.modules.crud;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.MappingTarget;

public interface DtoMapper<E extends EntityBase, D> {

    @BaseMappings
    void toEntity(D dto, @MappingTarget E entity);

    @InheritConfiguration(name = "toEntity")
    E updateFromDto(@MappingTarget E entity, D dto);
}
