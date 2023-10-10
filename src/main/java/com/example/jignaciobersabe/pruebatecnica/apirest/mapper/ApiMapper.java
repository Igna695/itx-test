package com.example.jignaciobersabe.pruebatecnica.apirest.mapper;

import com.example.jignaciobersabe.pruebatecnica.apirest.data.ResponseProductDto;
import com.example.jignaciobersabe.pruebatecnica.domain.model.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {

    ResponseProductDto productDtoToResponseProductDto(ProductDto productDto);

}
