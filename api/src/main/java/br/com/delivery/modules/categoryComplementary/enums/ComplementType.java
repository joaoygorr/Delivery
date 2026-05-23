package br.com.delivery.modules.categoryComplementary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ComplementType {
    ADD("Adicionar"),
    REMOVE("Remover"),
    SWAP("Trocar");

    private final String description;

    ComplementType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
