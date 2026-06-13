package br.com.delivery.modules.product.dtos;

import br.com.delivery.modules.crud.DtoBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends DtoBase {

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @NotNull(message = "Imagem não pode estar vazia")
    private MultipartFile img;

    @NotNull(message = "Preço não pode estar vazio")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Categoria não pode estar vazia")
    private Long categoryId;
}
