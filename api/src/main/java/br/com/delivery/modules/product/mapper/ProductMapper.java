package br.com.delivery.modules.product.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.product.Product;
import br.com.delivery.modules.product.records.ProductRecord;
import br.com.delivery.modules.product.records.ProductResponseRecord;
import br.com.delivery.modules.category.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public abstract class ProductMapper  {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(target = "category", source = "categoryId")
    public abstract Product toEntity(ProductRecord productRecord);

    @Mapping(target = "categoryId", source = "category")
    public abstract ProductResponseRecord toDto(Product product);

    Category mapLongToCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    Long mapCategorytoLong(Category category) {
        return category.getId();
    }

    byte[] mapMultipartFileToByte(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }
}
