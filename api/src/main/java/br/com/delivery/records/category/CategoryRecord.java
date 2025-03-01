package br.com.delivery.records.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRecord(Long idCategory,
                             @NotBlank(message = "Nome não pode estar vazio") String name) {
}
