package com.example.jignaciobersabe.pruebatecnica.domain.repository;

import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<ProductDto> getProductByDateAndProductIdAndBrandId(OffsetDateTime date, Long productId, Long brandId);
}
