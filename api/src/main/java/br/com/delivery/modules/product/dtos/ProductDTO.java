package br.com.delivery.modules.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    private String name;

    @NotNull(message = "Imagem não pode estar vazia")
    private MultipartFile img;

    @NotNull(message = "Preço não pode estar vazio")
    private BigDecimal price;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String description;

    @NotNull(message = "Categoria não pode estar vazia")
    private Long category;
}
