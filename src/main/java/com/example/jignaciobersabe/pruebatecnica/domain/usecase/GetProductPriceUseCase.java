package com.example.jignaciobersabe.pruebatecnica.domain.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;

import java.time.OffsetDateTime;

public interface GetProductPriceUseCase {

    ProductDto getProductByDateAndProductIdAndBrandId(OffsetDateTime date, Long productId, Long brandId)
            throws ProductNotFoundException;
}
