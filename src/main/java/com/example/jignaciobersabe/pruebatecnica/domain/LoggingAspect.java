package com.example.jignaciobersabe.pruebatecnica.domain;

import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final SendMessageUseCase sendMessageUseCase;

    @Before("execution(* com.example.jignaciobersabe.pruebatecnica.apirest.controller.ProductController.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        MessageLog messageLog = MessageLog.builder()
                .message(methodName)
                .timestamp(OffsetDateTime.now())
                .build();

        sendMessageUseCase.send(messageLog);
    }
}
