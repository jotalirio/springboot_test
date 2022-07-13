package org.lirio.test.springboot.app.repositories;

import lombok.val;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.lirio.test.springboot.app.utils.DateUtils.getDateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class PriceRepositoryIntegrationTest {

    @Autowired
    private PriceRepository priceRepository;

    @Order(1)
    @DisplayName("Given a productId, brandId and applicationDate should retrieve prices successfully")
    @Test
    void shouldFindByProductIdAndBrandIdAndApplicationDate() {
        // GIVEN
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        val formatter = getDateTimeFormatter();
        val mockedLocalDateTime = LocalDateTime.parse("2020-06-14-16.00.00", formatter);

        val expected = 2L;

        // WHEN
        val actual = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                mockedBrandId, mockedProductId, mockedLocalDateTime, mockedLocalDateTime);

        // THEN
        assertFalse(actual.isEmpty());
        assertEquals(expected, actual.size());
    }

    @Order(2)
    @DisplayName("Given a productId, brandId and applicationDate should not retrieve prices because there are no results")
    @Test
    void shouldNotFindByProductIdAndBrandIdAndApplicationDate() {
        // GIVEN
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        val formatter = getDateTimeFormatter();
        val mockedLocalDateTime = LocalDateTime.parse("2022-06-14-16.00.00", formatter);

        // WHEN
        val actual = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                mockedBrandId, mockedProductId, mockedLocalDateTime, mockedLocalDateTime);

        // THEN
        assertTrue(actual.isEmpty());
    }

}
