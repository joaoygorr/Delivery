package br.com.delivery.modules.product.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.product.Product;
import br.com.delivery.records.product.ProductRecord;
import br.com.delivery.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
@RequiredArgsConstructor
public abstract class ProductMapper {

    private final CategoryRepository categoryRepository;

    @Mapping(target = "category", source = "categoryId")
    public abstract Product toEntity(ProductRecord productRecord);

    Category mapCategory(Long id) {
        return categoryRepository.findById(id);
    }
}
