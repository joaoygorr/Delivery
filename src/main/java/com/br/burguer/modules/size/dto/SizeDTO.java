package com.br.burguer.modules.size.dto;

import com.br.burguer.modules.product.Product;
import com.br.burguer.modules.size.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class SizeDTO {

    private Long idSize;

    private Product product;

    @NotEmpty
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer maxCombination;

    public static Size toEntity(SizeDTO sizeDTO) {
        return new Size(
                sizeDTO.idSize,
                sizeDTO.product,
                sizeDTO.name,
                sizeDTO.price,
                sizeDTO.maxCombination
        );
    }

    public static SizeDTO toDto(Size size) {
        return new SizeDTO(
                size.getIdSize(),
                size.getProduct(),
                size.getName(),
                size.getPrice(),
                size.getMaxCombination()
        );
    }
}
