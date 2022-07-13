package org.lirio.test.springboot.app.utils;

import org.lirio.test.springboot.app.entities.Brand;
import org.lirio.test.springboot.app.entities.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataUtils {

    public static final String EURO = "EUR";


    private DataUtils() {
        throw new IllegalStateException("DataUtils class");
    }
    public static Price getPriceEntity(Long id, Long productId, Integer priority, BigDecimal price, String currency, Long brandId,
                                       LocalDateTime startDate, LocalDateTime endDate) {
        return Price.builder()
                .id(id)
                .productId(productId)
                .priority(priority)
                .price(price)
                .currency(currency)
                .brand(Brand.builder()
                        .id(1L)
                        .name("ZARA")
                        .build())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
