package com.example.jignaciobersabe.pruebatecnica.application.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.MessageRepository;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendMessageUseCaseImpl implements SendMessageUseCase {

    private final MessageRepository messageRepository;

    @Override
    public void send(MessageLog messageLog) {
        messageRepository.send(messageLog);
    }
}
