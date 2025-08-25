package br.com.delivery.modules.product.mapper;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.CategoryRepository;
import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.product.Product;
import br.com.delivery.modules.product.dtos.ProductDTO;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class ProductMapper implements DtoMapper<Product, ProductDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    Category mapLongToCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    byte[] mapMultipartFileToByte(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }
}
