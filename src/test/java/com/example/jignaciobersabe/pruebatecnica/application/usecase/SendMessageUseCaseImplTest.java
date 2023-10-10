package com.example.jignaciobersabe.pruebatecnica.application.usecase;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import com.example.jignaciobersabe.pruebatecnica.domain.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SendMessageUseCaseImpl.class})
@ExtendWith(SpringExtension.class)
class SendMessageUseCaseImplTest {
    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private SendMessageUseCaseImpl sendMessageUseCaseImpl;

    @Test
    void given_message_when_send_it_then_verify() {
        doNothing().when(messageRepository).send((MessageLog) any());
        sendMessageUseCaseImpl.send(new MessageLog());
        verify(messageRepository).send((MessageLog) any());
    }
}
