package br.com.delivery.records.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProductRecord(Long idProduct,
                            @NotBlank(message = "Nome não pode estar vazio") String name,
                            @NotNull(message = "Imagem não pode estar vazia") MultipartFile img,
                            @NotNull(message = "Preço não pode estar vazia") BigDecimal price,
                            @NotBlank(message = "Descrição não pode estar vazia") String description,
                            @NotNull(message = "Categoria não pode estar vazia") Long categoryId) {
}
