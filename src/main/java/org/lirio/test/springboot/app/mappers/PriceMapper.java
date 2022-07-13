package org.lirio.test.springboot.app.mappers;

import lombok.AllArgsConstructor;
import lombok.val;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.lirio.test.springboot.app.entities.Price;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.NotImplementedException;
import java.util.Objects;

import static org.lirio.test.springboot.app.utils.DateUtils.getDateTimeFormatter;

@Component
@AllArgsConstructor
public class PriceMapper implements EntityDtoMapper<Price, PriceDTO> {

    @Override
    public PriceDTO toDto(Price entity) {
        val formatter = getDateTimeFormatter();
        if (Objects.nonNull(entity)) {
            return PriceDTO.builder()
                    .productId(entity.getProductId())
                    .brandId(entity.getBrand().getId())
                    .priceList(entity.getId())
                    .startDate(formatter.format(entity.getStartDate()))
                    .endDate(formatter.format(entity.getEndDate()))
                    .priority(entity.getPriority())
                    .price(entity.getPrice())
                    .currency(entity.getCurrency())
                    .build();
        }

        return null;
    }

    @Override
    public Price toEntity(PriceDTO dto) {
        throw new NotImplementedException();
    }
}
