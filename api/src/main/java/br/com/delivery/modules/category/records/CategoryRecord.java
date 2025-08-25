package br.com.delivery.modules.category.records;

import jakarta.validation.constraints.NotBlank;

public record CategoryRecord(Long id,
                             @NotBlank(message = "Nome não pode estar vazio") String name) {
}
