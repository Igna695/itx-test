package com.example.jignaciobersabe.pruebatecnica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageLog {
    private String message;
    private OffsetDateTime timestamp;
}