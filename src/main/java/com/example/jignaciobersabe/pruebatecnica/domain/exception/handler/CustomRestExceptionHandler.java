package com.example.jignaciobersabe.pruebatecnica.domain.exception.handler;

import com.example.jignaciobersabe.pruebatecnica.apirest.data.ErrorResponseDto;
import com.example.jignaciobersabe.pruebatecnica.domain.exception.ProductNotFoundException;
import com.example.jignaciobersabe.pruebatecnica.domain.model.MessageLog;
import com.example.jignaciobersabe.pruebatecnica.domain.usecase.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private final SendMessageUseCase sendMessageUseCase;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception exception) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setCode("500");
        error.setMessage("Error occurred, check required fields or contact with the API Administrator");

        MessageLog messageLog = MessageLog.builder()
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        sendMessageUseCase.send(messageLog);

        return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ProductNotFoundException exception) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setCode("400");
        error.setMessage("Product or price not found");
        MessageLog messageLog = MessageLog.builder()
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        sendMessageUseCase.send(messageLog);
        return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
