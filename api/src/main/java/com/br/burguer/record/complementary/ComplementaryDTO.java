package com.br.burguer.record.complementary;

import com.br.burguer.modules.complementary.Complementary;
import com.br.burguer.modules.size.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ComplementaryDTO(Long idComplementary, @NotNull Size size,
                               BigDecimal price, @NotEmpty String name,Integer quantity) {

    public static Complementary toEntity(ComplementaryDTO complementaryDTO) {
        return new Complementary(
                complementaryDTO.idComplementary,
                complementaryDTO.size,
                complementaryDTO.price,
                complementaryDTO.name,
                complementaryDTO.quantity
        );
    }

    public static ComplementaryDTO toDto(Complementary complementary) {
        return new ComplementaryDTO(
                complementary.getIdComplementary(),
                complementary.getSize(),
                complementary.getPrice(),
                complementary.getName(),
                complementary.getQuantity()
        );
    }
}
