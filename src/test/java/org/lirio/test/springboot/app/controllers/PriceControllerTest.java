package org.lirio.test.springboot.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.*;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.lirio.test.springboot.app.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.lirio.test.springboot.app.controllers.PriceController.URI;
import static org.lirio.test.springboot.app.utils.DataUtils.EURO;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(PriceController.class)
class PriceControllerTest {

    private static final String URL = "/api" + URI;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceService priceService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Order(1)
    @DisplayName("Given a brandId, productId and applicationDate should get priority price at response body successfully")
    @Test
    void shouldGetPriorityPrice() throws Exception {
        // GIVEN
        val mockedStartDate = "2020-06-14-15.00.00";
        val mockedEndDate = "2020-06-14-18.30.00";
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        val maybePriceDTO = Optional.of(PriceDTO.builder()
                .priceList(2L)
                .productId(mockedProductId)
                .priority(1)
                .price(BigDecimal.valueOf(25.45))
                .currency(EURO)
                .brandId(mockedBrandId)
                .startDate(mockedStartDate)
                .endDate(mockedEndDate)
                .build());
        when(priceService.findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class))).thenReturn(maybePriceDTO);

        // WHEN
        val pathParamBrandId = String.valueOf(mockedBrandId);
        val pathParamProductId = String.valueOf(mockedProductId);
        val paramName = "date";
        val query = "2020-06-14-16.00.00";
        mvc.perform(get(URL, pathParamBrandId, pathParamProductId).param(paramName, query).contentType(MediaType.APPLICATION_JSON))

        // THEN
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(maybePriceDTO.get())));

        verify(priceService).findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class));
    }

    @Order(2)
    @DisplayName("Given a brandId, productId and applicationDate should not get priority price because there are no results")
    @Test
    void shouldNotGetPriorityPrice() throws Exception {
        // GIVEN
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        Optional<PriceDTO> maybePriceDTO = Optional.empty();
        when(priceService.findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class))).thenReturn(maybePriceDTO);

        // WHEN
        val pathParamBrandId = String.valueOf(mockedBrandId);
        val pathParamProductId = String.valueOf(mockedProductId);
        val paramName = "date";
        val query = "2022-06-14-16.00.00";
        mvc.perform(get(URL, pathParamBrandId, pathParamProductId).param(paramName, query).contentType(MediaType.APPLICATION_JSON))

        // THEN
            .andExpect(status().isNotFound());

        verify(priceService).findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class));
    }

    @Order(3)
    @DisplayName("Given a missing brandId, productId and applicationDate should not get priority price")
    @Test
    void shouldNotGetPriorityPriceIfBrandIdIsMissing() throws Exception {
        // GIVEN
        val mockedProductId = 35455L;

        Optional<PriceDTO> maybePriceDTO = Optional.empty();
        when(priceService.findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class))).thenReturn(maybePriceDTO);

        // WHEN
        val missingPathParamBrandId = "";
        val pathParamProductId = String.valueOf(mockedProductId);
        val paramName = "date";
        val query = "2022-06-14-16.00.00";
        mvc.perform(get(URL, missingPathParamBrandId, pathParamProductId).param(paramName, query).contentType(MediaType.APPLICATION_JSON))

        // THEN
            .andExpect(status().isNotFound());
    }

    @Order(4)
    @DisplayName("Given a brandId, missing productId and applicationDate should not get priority price")
    @Test
    void shouldNotGetPriorityPriceIfProductIdIsMissing() throws Exception {
        // GIVEN
        val mockedBrandId = 1L;

        Optional<PriceDTO> maybePriceDTO = Optional.empty();
        when(priceService.findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class))).thenReturn(maybePriceDTO);

        // WHEN
        val pathParamBrandId = String.valueOf(mockedBrandId);
        val missingPathParamProductId = "";
        val paramName = "date";
        val query = "2022-06-14-16.00.00";
        mvc.perform(get(URL, pathParamBrandId, missingPathParamProductId).param(paramName, query).contentType(MediaType.APPLICATION_JSON))

        // THEN
            .andExpect(status().isNotFound());
    }

    @Order(5)
    @DisplayName("Given a brandId, productId and missing applicationDate should not get priority price")
    @Test
    void shouldNotGetPriorityPriceIfApplicationDateIsMissing() throws Exception {
        // GIVEN
        val mockedProductId = 35455L;
        val mockedBrandId = 1L;

        Optional<PriceDTO> maybePriceDTO = Optional.empty();
        when(priceService.findPriorityPrice(any(Long.class), any(Long.class), any(LocalDateTime.class))).thenReturn(maybePriceDTO);

        // WHEN
        val pathParamBrandId = String.valueOf(mockedBrandId);
        val pathParamProductId = String.valueOf(mockedProductId);
        val paramName = "date";
        val query = "";
        mvc.perform(get(URL, pathParamBrandId, pathParamProductId).param(paramName, query).contentType(MediaType.APPLICATION_JSON))

        // THEN
            .andExpect(status().isBadRequest());
    }
}
