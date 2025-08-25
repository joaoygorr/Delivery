package br.com.delivery.modules.product.records;

import java.math.BigDecimal;

public record ProductResponseRecord(Long id,
                                    String name,
                                    byte[] img,
                                    BigDecimal price,
                                    String description,
                                    Long categoryId) {
}
