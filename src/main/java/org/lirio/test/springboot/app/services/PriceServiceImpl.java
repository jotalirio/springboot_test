package org.lirio.test.springboot.app.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.lirio.test.springboot.app.dtos.PriceDTO;
import org.lirio.test.springboot.app.entities.Price;
import org.lirio.test.springboot.app.mappers.PriceMapper;
import org.lirio.test.springboot.app.repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper mapper;

    @Override
    public Optional<PriceDTO> findPriorityPrice(final Long brandId, final Long productId, final LocalDateTime applicationDate) {
        val maybePrice = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        brandId, productId, applicationDate, applicationDate)
                        .stream()
                        .max(Comparator.comparing(Price::getPriority));

        return maybePrice.map(mapper::toDto);
    }
}
