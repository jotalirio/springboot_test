package org.lirio.test.springboot.app.repositories;

import org.lirio.test.springboot.app.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(final Long brandId,
                                                                                             final Long productId,
                                                                                             final LocalDateTime startDate,
                                                                                             final LocalDateTime endDate);
}
