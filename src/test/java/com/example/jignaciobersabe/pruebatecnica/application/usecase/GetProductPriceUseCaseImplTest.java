package com.example.jignaciobersabe.pruebatecnica.application.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {GetProductPriceUseCaseImpl.class})
@ExtendWith(SpringExtension.class)
class GetProductPriceUseCaseImplTest {
    @Autowired
    private GetProductPriceUseCaseImpl getProductPriceUseCaseImpl;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    void given_mocked_data_when_call_getProductByDateAndProductIdAndBrandId_then_return_product()
            throws ProductNotFoundException {
        ProductDto productDto = new ProductDto();
        when(priceRepository.getProductByDateAndProductIdAndBrandId((OffsetDateTime) any(), (Long) any(), (Long) any()))
                .thenReturn(Optional.of(productDto));
        assertSame(productDto, getProductPriceUseCaseImpl.getProductByDateAndProductIdAndBrandId(null, 1L, 1L));
        verify(priceRepository)
                .getProductByDateAndProductIdAndBrandId((OffsetDateTime) any(), (Long) any(), (Long) any());
    }

    @Test
    void given_wrong_data_when_call_getProductByDateAndProductIdAndBrandId_then_throws_exception()
            throws ProductNotFoundException {
        when(priceRepository.getProductByDateAndProductIdAndBrandId((OffsetDateTime) any(), (Long) any(), (Long) any()))
                .thenReturn(Optional.empty());
        assertThrows(
                ProductNotFoundException.class,
                () -> getProductPriceUseCaseImpl.getProductByDateAndProductIdAndBrandId(null, 1L, 1L));
        verify(priceRepository)
                .getProductByDateAndProductIdAndBrandId((OffsetDateTime) any(), (Long) any(), (Long) any());
    }
}
