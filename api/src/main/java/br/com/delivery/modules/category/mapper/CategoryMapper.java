package br.com.delivery.modules.category.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.records.category.CategoryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CategoryMapper {

    public abstract Category toEntity(CategoryRecord categoryRecord);

    public abstract CategoryRecord toDto(Category category);

    @Mapping(target = "idCategory", ignore = true)
    public abstract void updateCategory(CategoryRecord dto, @MappingTarget Category entity);
}
