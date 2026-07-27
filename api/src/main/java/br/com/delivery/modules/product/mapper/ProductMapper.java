package br.com.delivery.modules.product.mapper;

import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.CategoryRepository;
import br.com.delivery.modules.crud.DtoMapper;
import br.com.delivery.modules.crud.Image;
import br.com.delivery.modules.product.Product;
import br.com.delivery.modules.product.dtos.ProductDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public abstract class ProductMapper implements DtoMapper<Product, ProductDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Mapping(source = "categoryId", target = "category")
    public abstract void toEntity(ProductDTO dto, @MappingTarget Product entity);

    @Override
    public abstract ProductDTO toDto(Product entity);

    Category mapCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new Exception404(String.format("Categoria com ID %d não encontrada", id)));
    }

    Long map(Category category) {
        return category != null ? category.getId() : null;
    }

    Image mapImage(MultipartFile file) {
        try {
            return new Image(null, file.getBytes(), file.getOriginalFilename(), file.getContentType(),
                    file.getSize());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter imagem", e);
        }
    }

    MultipartFile mapMultiparFIle(Image img) {
        return img
    }

    @AfterMapping
    protected void mapImage(ProductDTO dto, @MappingTarget Product entity) {
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            entity.setImage(mapImage(dto.getImage()));
        } else if (dto.getImageUrl() != null) {
            entity.setImage(new Image(dto.getImageUrl()));
        }
    }
}
