package br.com.delivery.modules.product.mapper;

import br.com.delivery.mapper.DtoMapper;
import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.product.Product;
import br.com.delivery.records.product.ProductRecord;
import br.com.delivery.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public abstract class ProductMapper implements DtoMapper<Product, ProductRecord> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Mapping(target = "category", source = "categoryId")
    public abstract Product toEntity(ProductRecord productRecord, @MappingTarget Product entity);

    Category mapCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado"));
    }
}
