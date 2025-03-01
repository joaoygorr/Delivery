package br.com.delivery.records.product;

import java.math.BigDecimal;

public record ProductResponseRecord(Long idProduct,
                                    String name,
                                    byte[] img,
                                    BigDecimal price,
                                    String description,
                                    Long categoryId) {
}
