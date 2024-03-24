package com.example.metricsproducer.errorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandleInterruptExceptionService {

    @ExceptionHandler
    public void interruptExceptionHandler(InterruptedException e) {
        log.error("При отправке метрики получены ошибки {}", e.getMessage());
    }
}
