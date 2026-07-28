package br.com.delivery.modules.product.dtos;

import br.com.delivery.modules.crud.DtoBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends DtoBase {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @JsonIgnore
    private transient MultipartFile imageFile;

    private String imageUrl;

    @NotNull(message = "Preço não pode estar vazio")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Categoria não pode estar vazia")
    private Long categoryId;

    private String categoryName;

    private String imageName;

    private String imageType;
}
