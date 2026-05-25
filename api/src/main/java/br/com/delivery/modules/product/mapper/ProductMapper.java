package br.com.delivery.modules.product.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.CategoryRepository;
import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.product.Product;
import br.com.delivery.modules.product.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class ProductMapper implements DtoMapper<Product, ProductDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(source = "category.id", target = "id")
    public abstract ProductDTO toDto(Product product);

    public Category map(Long id) {
        if (id == null) return null;
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));
    }

    public Long map(Category category) {
        return category != null ? category.getId() : null;
    }

    byte[] mapMultipartFileToByte(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }
}
