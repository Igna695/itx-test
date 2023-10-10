package com.example.jignaciobersabe.pruebatecnica.apirest.controller;

import com.example.jignaciobersabe.pruebatecnica.apirest.data.ResponseProductDto;
import com.example.jignaciobersabe.pruebatecnica.apirest.mapper.ApiMapper;
import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.GetProductPriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private ApiMapper apiMapper;

    @MockBean
    private GetProductPriceUseCase getProductPriceUseCase;

    @Autowired
    private ProductController productController;

    @Test
    void given_date_productId_and_brandId_when_call_endpoint_then_return_product() throws ProductNotFoundException {
        when(getProductPriceUseCase.getProductByDateAndProductIdAndBrandId(
                        (OffsetDateTime) any(), (Long) any(), (Long) any()))
                .thenReturn(new ProductDto());
        when(apiMapper.productDtoToResponseProductDto((ProductDto) any())).thenReturn(new ResponseProductDto());
        ResponseEntity<ResponseProductDto> actual_getProductsResult = productController._getProducts(null, 1L, 1L);
        assertTrue(actual_getProductsResult.hasBody());
        assertTrue(actual_getProductsResult.getHeaders().isEmpty());
        assertEquals(200, actual_getProductsResult.getStatusCodeValue());
        verify(getProductPriceUseCase)
                .getProductByDateAndProductIdAndBrandId((OffsetDateTime) any(), (Long) any(), (Long) any());
        verify(apiMapper).productDtoToResponseProductDto((ProductDto) any());
    }
}
