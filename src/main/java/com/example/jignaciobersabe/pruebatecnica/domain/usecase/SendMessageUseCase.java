package com.example.jignaciobersabe.pruebatecnica.domain.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;

public interface SendMessageUseCase {
    void send(MessageLog messageLog);
}
