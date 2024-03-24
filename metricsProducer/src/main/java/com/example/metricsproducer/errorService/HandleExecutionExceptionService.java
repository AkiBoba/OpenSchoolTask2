package com.example.metricsproducer.errorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestControllerAdvice
public class HandleExecutionExceptionService {

    @ExceptionHandler
    public void executionExceptionHandler(ExecutionException e) {
       log.error("При отправке метрики получены ошибки {}", e.getMessage());
    }
}
