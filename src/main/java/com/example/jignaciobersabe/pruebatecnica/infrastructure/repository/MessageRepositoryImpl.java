package com.example.jignaciobersabe.pruebatecnica.infrastructure.repository;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.MessageRepository;
import com.example.jignaciobersabe.pruebatecnica.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final KafkaTemplate<Long, MessageLog> kafkaTemplate;

    private final ProductMapper productMapper;

    @Value(value = "${kafka.output-topic}")
    private String OUTPUT_TOPIC;

    @Override
    public void send(MessageLog messageLog) {
        kafkaTemplate.send(OUTPUT_TOPIC, messageLog);
    }
}
