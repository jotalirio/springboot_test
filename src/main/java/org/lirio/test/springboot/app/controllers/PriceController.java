package org.lirio.test.springboot.app.controllers;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.lirio.test.springboot.app.services.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.lirio.test.springboot.app.utils.DateUtils.PATTERN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PriceController {

    public static final String URI = "/brands/{brandId}/products/{productId}";

    private final PriceService priceService;

    @GetMapping(URI)
    public ResponseEntity<?> getPriorityPrice(@PathVariable final Long brandId, @PathVariable final Long productId,
                                              @RequestParam @DateTimeFormat(pattern = PATTERN) final LocalDateTime date) {
        val maybePrice = priceService.findPriorityPrice(brandId, productId, date);
        if (!maybePrice.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(maybePrice.get());
    }
}
