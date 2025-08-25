package br.com.delivery.modules.category.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.dtos.CategoryDTO;
import br.com.delivery.modules.crud.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class CategoryMapper implements DtoMapper<Category, CategoryDTO> {
}
