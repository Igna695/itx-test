package com.example.jignaciobersabe.pruebatecnica.infrastructure.repository;

import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.PriceRepository;
import com.example.jignaciobersabe.pruebatecnica.infrastructure.entity.Price;
import com.example.jignaciobersabe.pruebatecnica.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final InH2PriceRepository priceRepository;

    private final ProductMapper productMapper;

    @Override
    public Optional<ProductDto> getProductByDateAndProductIdAndBrandId(
            OffsetDateTime date, Long productId, Long brandId) {
        List<Price> prices = priceRepository.getPrice(date, productId, brandId);

        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .map(this.productMapper::priceToProductDto);
    }
}
