package com.example.jignaciobersabe.pruebatecnica.apirest.controller;

import com.example.jignaciobersabe.pruebatecnica.apirest.data.ResponseProductDto;
import com.example.jignaciobersabe.pruebatecnica.apirest.mapper.ApiMapper;
import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.GetProductPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@RestController
public class ProductController implements ApiApi {

    private final GetProductPriceUseCase getProductPriceUseCase;
    private final ApiMapper mapper;

    @Override
    public ResponseEntity<ResponseProductDto> _getProducts(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date, Long productId, Long brandId)
            throws ProductNotFoundException {
        ProductDto productDto = getProductPriceUseCase.getProductByDateAndProductIdAndBrandId(date, productId, brandId);
        return ResponseEntity.ok(this.mapper.productDtoToResponseProductDto(productDto));
    }
}
