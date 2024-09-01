package com.br.burguer.modules.complementary.dto;

import com.br.burguer.modules.complementary.Complementary;
import com.br.burguer.modules.size.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ComplementaryDTO {

    private Long idComplementary;

    @NotNull
    private Size size;

    private BigDecimal price;

    @NotEmpty
    private String name;

    private Integer quantity;

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
