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
    @Mapping(target = "image", ignore = true)
    public abstract void toEntity(ProductDTO dto, @MappingTarget Product entity);

    @Override
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imageUrl", source = "image", qualifiedByName = "mapImageUrl")
    @Mapping(source = "category", target = "categoryId")
    public abstract ProductDTO toDto(Product entity);

    Category mapCategory(Long id) {
        if (id == null) return null;
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new Exception404(String.format("Categoria com ID %d não encontrada", id)));
    }

    Long map(Category category) {
        return category != null ? category.getId() : null;
    }

    @Named("mapImageUrl")
    public String mapImageUrl(Image image) {
        if (image == null) return null;
        if (image.isFromUrl()) {
            return image.getUrlImage();
        }
        return null;
    }

    @AfterMapping
    protected void handleImage(ProductDTO dto, @MappingTarget Product entity) {
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                MultipartFile file = dto.getImage();
                entity.setImage(new Image(null, file.getBytes(), file.getOriginalFilename(),
                        file.getContentType(), file.getSize()));
            } catch (IOException e) {
                throw new RuntimeException("Erro ao converter imagem", e);
            }
        } else if (dto.getImageUrl() != null && !dto.getImageUrl().isBlank()) {
            entity.setImage(new Image(dto.getImageUrl()));
        }
    }
}
