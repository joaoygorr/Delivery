package br.com.delivery.modules.order.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PREPARING("Em preparação"),
    SENT("Enviado"),
    DELIVERED("Entregue");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
