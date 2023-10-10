package com.example.jignaciobersabe.pruebatecnica.infrastructure.repository;

import com.example.jignaciobersabe.pruebatecnica.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PriceRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class PriceRepositoryImplTest {
    @MockBean
    private InH2PriceRepository inH2PriceRepository;

    @Autowired
    private PriceRepositoryImpl priceRepositoryImpl;

    @MockBean
    private ProductMapper productMapper;

    @Test
    void given_mocked_data_when_call_getProductByDateAndProductIdAndBrandId_then_return_value() {
        when(inH2PriceRepository.getPrice((OffsetDateTime) any(), (Long) any(), (Long) any()))
                .thenReturn(new ArrayList<>());
        assertFalse(priceRepositoryImpl
                .getProductByDateAndProductIdAndBrandId(null, 1L, 1L)
                .isPresent());
        verify(inH2PriceRepository).getPrice((OffsetDateTime) any(), (Long) any(), (Long) any());
    }
}
