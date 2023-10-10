package com.example.jignaciobersabe.pruebatecnica.application.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.PriceRepository;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.GetProductPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetProductPriceUseCaseImpl implements GetProductPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public ProductDto getProductByDateAndProductIdAndBrandId(OffsetDateTime date, Long productId, Long brandId)
            throws ProductNotFoundException {
        Optional<ProductDto> productDto =
                priceRepository.getProductByDateAndProductIdAndBrandId(date, productId, brandId);
        if (productDto.isEmpty()) {
            throw new ProductNotFoundException("Product or price not found");
        } else {
            return productDto.get();
        }
    }
}
