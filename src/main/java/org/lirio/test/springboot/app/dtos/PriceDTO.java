package org.lirio.test.springboot.app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class PriceDTO implements Serializable {

    private static final long serialVersionUID = -5120690437668837142L;

    private Long productId;
    private Long brandId;
    private Long priceList;
    private String startDate;
    private String endDate;
    @JsonIgnore
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
