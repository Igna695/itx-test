package com.example.jignaciobersabe.pruebatecnica.infrastructure.config;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrapServers}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<Long, MessageLog> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.RETRIES_CONFIG, 1);

        //        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        //        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        DefaultKafkaProducerFactory<Long, MessageLog> factory = new DefaultKafkaProducerFactory<>(configProps);
        //        factory.setTransactionIdPrefix(transactionalId + "transactionId");
        return factory;
    }

    @Bean
    public KafkaTemplate<Long, MessageLog> userKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}