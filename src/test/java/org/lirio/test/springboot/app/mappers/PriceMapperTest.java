package org.lirio.test.springboot.app.mappers;

import lombok.val;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.*;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.lirio.test.springboot.app.utils.DataUtils.EURO;
import static org.lirio.test.springboot.app.utils.DataUtils.getPriceEntity;
import static org.lirio.test.springboot.app.utils.DateUtils.getDateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PriceMapperTest {

    @Autowired
    private PriceMapper mapper;

    @Order(1)
    @DisplayName("Given a Price entity should map Price DTO successfully")
    @Test
    void shouldMapDtoFromEntity() {
        // GIVEN
        val mockedStartDate = LocalDateTime.now();
        val mockedEndDate = mockedStartDate.plusMonths(2);
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        val entity = getPriceEntity(1L, mockedProductId, 0, BigDecimal.valueOf(35.50), EURO,
                mockedBrandId, mockedStartDate, mockedEndDate);

        val formatter = getDateTimeFormatter();
        val expected = PriceDTO.builder()
                .priceList(1L)
                .productId(mockedProductId)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency(EURO)
                .brandId(mockedBrandId)
                .startDate(formatter.format(mockedStartDate))
                .endDate(formatter.format(mockedEndDate))
                .build();

        // WHEN
        val actual = mapper.toDto(entity);

        // THEN
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Order(2)
    @DisplayName("Given a Price dto should fail to map Price entity")
    @Test
    void shouldFailMapEntityFromDto() {
        assertThrows(NotImplementedException.class, () -> mapper.toEntity(null));
    }
}
