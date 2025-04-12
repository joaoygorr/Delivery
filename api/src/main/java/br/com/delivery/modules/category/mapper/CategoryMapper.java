package br.com.delivery.modules.category.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.records.category.CategoryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public abstract class CategoryMapper {

    public abstract Category toEntity(CategoryRecord categoryRecord);

    public abstract CategoryRecord toDto(Category category);

    public abstract void updateCategory(CategoryRecord dto, @MappingTarget Category entity);
}
