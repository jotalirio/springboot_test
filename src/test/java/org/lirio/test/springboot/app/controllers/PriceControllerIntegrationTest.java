package org.lirio.test.springboot.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.*;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate client;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @DisplayName("Given a brandId = 1 (ZARA), productId = 35455 and applicationDate = 2020-06-14-10.00.00 should get priority price")
    @Test
    @Order(1)
    void test1() {
        // GIVEN
        String url = "/api/brands/1/products/35455?date=2020-06-14-10.00.00";
        val expectedProductId = 35455L;
        val expectedBrandId = 1L;
        val expectedPriceList = 1L;
        val expectedStartDate = "2020-06-14-00.00.00";
        val expectedEndDate = "2020-12-31-23.59.59";
        val expectedPrice = BigDecimal.valueOf(35.50).setScale(2);

        // WHEN
        val response = client.getForEntity(url, PriceDTO.class);

        // THEN
        val priceDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(priceDTO);
        assertEquals(expectedProductId, priceDTO.getProductId());
        assertEquals(expectedBrandId, priceDTO.getBrandId());
        assertEquals(expectedPriceList, priceDTO.getPriceList());
        assertEquals(expectedStartDate, priceDTO.getStartDate());
        assertEquals(expectedEndDate, priceDTO.getEndDate());
        assertEquals(expectedPrice, priceDTO.getPrice());
    }

    @DisplayName("Given a brandId = 1 (ZARA), productId = 35455 and applicationDate = 2020-06-14-16.00.00 should get priority price")
    @Test
    @Order(2)
    void test2() {
        // GIVEN
        String url = "/api/brands/1/products/35455?date=2020-06-14-16.00.00";
        val expectedProductId = 35455L;
        val expectedBrandId = 1L;
        val expectedPriceList = 2L;
        val expectedStartDate = "2020-06-14-15.00.00";
        val expectedEndDate = "2020-06-14-18.30.00";
        val expectedPrice = BigDecimal.valueOf(25.45);

        // WHEN
        val response = client.getForEntity(url, PriceDTO.class);

        // THEN
        val priceDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(priceDTO);
        assertEquals(expectedProductId, priceDTO.getProductId());
        assertEquals(expectedBrandId, priceDTO.getBrandId());
        assertEquals(expectedPriceList, priceDTO.getPriceList());
        assertEquals(expectedStartDate, priceDTO.getStartDate());
        assertEquals(expectedEndDate, priceDTO.getEndDate());
        assertEquals(expectedPrice, priceDTO.getPrice());
    }

    @DisplayName("Given a brandId = 1 (ZARA), productId = 35455 and applicationDate = 2020-06-14-21.00.00 should get priority price")
    @Test
    @Order(3)
    void test3() {
        // GIVEN
        String url = "/api/brands/1/products/35455?date=2020-06-14-21.00.00";
        val expectedProductId = 35455L;
        val expectedBrandId = 1L;
        val expectedPriceList = 1L;
        val expectedStartDate = "2020-06-14-00.00.00";
        val expectedEndDate = "2020-12-31-23.59.59";
        val expectedPrice = BigDecimal.valueOf(35.50).setScale(2);

        // WHEN
        val response = client.getForEntity(url, PriceDTO.class);

        // THEN
        val priceDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(priceDTO);
        assertEquals(expectedProductId, priceDTO.getProductId());
        assertEquals(expectedBrandId, priceDTO.getBrandId());
        assertEquals(expectedPriceList, priceDTO.getPriceList());
        assertEquals(expectedStartDate, priceDTO.getStartDate());
        assertEquals(expectedEndDate, priceDTO.getEndDate());
        assertEquals(expectedPrice, priceDTO.getPrice());
    }

    @DisplayName("Given a brandId = 1 (ZARA), productId = 35455 and applicationDate = 2020-06-15-10.00.00 should get priority price")
    @Test
    @Order(4)
    void test4() {
        // GIVEN
        String url = "/api/brands/1/products/35455?date=2020-06-15-10.00.00";
        val expectedProductId = 35455L;
        val expectedBrandId = 1L;
        val expectedPriceList = 3L;
        val expectedStartDate = "2020-06-15-00.00.00";
        val expectedEndDate = "2020-06-15-11.00.00";
        val expectedPrice = BigDecimal.valueOf(30.50).setScale(2);

        // WHEN
        val response = client.getForEntity(url, PriceDTO.class);

        // THEN
        val priceDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(priceDTO);
        assertEquals(expectedProductId, priceDTO.getProductId());
        assertEquals(expectedBrandId, priceDTO.getBrandId());
        assertEquals(expectedPriceList, priceDTO.getPriceList());
        assertEquals(expectedStartDate, priceDTO.getStartDate());
        assertEquals(expectedEndDate, priceDTO.getEndDate());
        assertEquals(expectedPrice, priceDTO.getPrice());
    }

    @DisplayName("Given a brandId = 1 (ZARA), productId = 35455 and applicationDate = 2020-06-16-21.00.00 should get priority price")
    @Test
    @Order(5)
    void test5() {
        // GIVEN
        String url = "/api/brands/1/products/35455?date=2020-06-16-21.00.00";
        val expectedProductId = 35455L;
        val expectedBrandId = 1L;
        val expectedPriceList = 4L;
        val expectedStartDate = "2020-06-15-16.00.00";
        val expectedEndDate = "2020-12-31-23.59.59";
        val expectedPrice = BigDecimal.valueOf(38.95);

        // WHEN
        val response = client.getForEntity(url, PriceDTO.class);

        // THEN
        val priceDTO = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(priceDTO);
        assertEquals(expectedProductId, priceDTO.getProductId());
        assertEquals(expectedBrandId, priceDTO.getBrandId());
        assertEquals(expectedPriceList, priceDTO.getPriceList());
        assertEquals(expectedStartDate, priceDTO.getStartDate());
        assertEquals(expectedEndDate, priceDTO.getEndDate());
        assertEquals(expectedPrice, priceDTO.getPrice());
    }
}