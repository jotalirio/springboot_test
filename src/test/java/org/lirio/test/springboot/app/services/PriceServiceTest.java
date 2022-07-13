package org.lirio.test.springboot.app.services;

import lombok.val;
import org.junit.jupiter.api.*;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.lirio.test.springboot.app.entities.Price;
import org.lirio.test.springboot.app.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.lirio.test.springboot.app.utils.DataUtils.EURO;
import static org.lirio.test.springboot.app.utils.DataUtils.getPriceEntity;
import static org.lirio.test.springboot.app.utils.DateUtils.getDateTimeFormatter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PriceServiceTest {

	@MockBean
	private PriceRepository priceRepository;

	@Autowired
	private PriceService priceService;

	@Order(1)
	@DisplayName("Given a list of prices should find the priority price successfully")
	@Test
	void shouldFindPriorityPrice() {
		// GIVEN
		val mockedStartDate = LocalDateTime.now();
		val mockedEndDate = mockedStartDate.plusMonths(2);
		val mockedProductId = 35455L;
		val mockedBrandId = 1L;
		val mockedApplicationDate = mockedStartDate.plusMonths(1);

		val entityPriorityZero = getPriceEntity(1L, mockedProductId, 0, BigDecimal.valueOf(35.50), EURO,
				mockedBrandId, mockedStartDate, mockedEndDate);
		val entityPriorityOne = getPriceEntity(2L, mockedProductId, 1, BigDecimal.valueOf(25.45), EURO,
				mockedBrandId, mockedStartDate, mockedEndDate);

		val prices = Arrays.asList(entityPriorityZero, entityPriorityOne);
		when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(Long.class),
				any(Long.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(prices);

		val formatter = getDateTimeFormatter();
		val expected = PriceDTO.builder()
				.priceList(2L)
				.productId(mockedProductId)
				.priority(1)
				.price(BigDecimal.valueOf(25.45))
				.currency(EURO)
				.brandId(mockedBrandId)
				.startDate(formatter.format(mockedStartDate))
				.endDate(formatter.format(mockedEndDate))
				.build();

		// WHEN
		val actual = priceService.findPriorityPrice(mockedBrandId, mockedProductId, mockedApplicationDate).orElseThrow();

		// THEN
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

	@Order(2)
	@DisplayName("Given a list of prices should not find the priority price because there are no results")
	@Test
	void shouldNotFindPriorityPrice() {
		// GIVEN
		val mockedProductId = 35455L;
		val mockedBrandId = 1L;
		val mockedApplicationDate = LocalDateTime.now();

		val prices = new ArrayList<Price>();
		when(priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(Long.class),
				any(Long.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(prices);

		// WHEN
		val actual = priceService.findPriorityPrice(mockedBrandId, mockedProductId, mockedApplicationDate);

		// THEN
		assertNotNull(actual);
		assertFalse(actual.isPresent());
	}
}
