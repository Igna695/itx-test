package com.example.jignaciobersabe.pruebatecnica.domain.repository;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;

public interface MessageRepository {

    void send(MessageLog messageLog);
}
