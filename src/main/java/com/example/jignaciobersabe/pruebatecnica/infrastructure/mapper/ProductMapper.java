package com.example.jignaciobersabe.pruebatecnica.infrastructure.mapper;

import com.example.jignaciobersabe.pruebatecnica.apirest.data.ResponseProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import com.example.jignaciobersabe.pruebatecnica.infrastructure.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "priceList", source = "priceList.id")
    ProductDto priceToProductDto(Price price);

}
