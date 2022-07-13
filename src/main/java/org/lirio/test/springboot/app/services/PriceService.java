package org.lirio.test.springboot.app.services;

import org.lirio.test.springboot.app.dtos.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<PriceDTO> findPriorityPrice(final Long brandId, final Long productId, final LocalDateTime applicationDate);

}
