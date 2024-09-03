package com.br.burguer.record.size;

import com.br.burguer.modules.Product;
import com.br.burguer.modules.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SizeDTO(Long idSize, Product product, @NotEmpty String name, @NotNull Integer maxCombination) {

    public static Size toEntity(SizeDTO sizeDTO) {
        return new Size(
                sizeDTO.idSize,
                sizeDTO.product,
                sizeDTO.name,
                sizeDTO.maxCombination
        );
    }

    public static SizeDTO toDto(Size size) {
        return new SizeDTO(
                size.getIdSize(),
                size.getProduct(),
                size.getName(),
                size.getMaxCombination()
        );
    }
}
