package br.com.delivery.modules.product.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.product.Product;
import br.com.delivery.records.product.ProductRecord;
import br.com.delivery.repositories.CategoryRepository;
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
    public abstract ProductRecord toDto(Product product);

    Category mapLongToCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado"));
    }

    Long mapCategorytoLong(Category category) {
        return category.getIdCategory();
    }

    byte[] mapMultipartFileToByte(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }

    MultipartFile matByteToMultiparFile(byte[] bytes) {
        return new CustomMultipartFile(bytes);
    }
}
