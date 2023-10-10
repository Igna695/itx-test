package com.example.jignaciobersabe.pruebatecnica.infrastructure.repository;

import com.example.jignaciobersabe.pruebatecnica.infrastructure.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface InH2PriceRepository extends JpaRepository<Price, Long> {

    @Query(
            value =
                    "Select * from prices where :date >= start_date and :date <= end_date and product_id = :productId and brand_id = :brandId",
            nativeQuery = true)
    List<Price> getPrice(
            @Param("date") OffsetDateTime date, @Param("productId") Long productId, @Param("brandId") Long brandId);
}