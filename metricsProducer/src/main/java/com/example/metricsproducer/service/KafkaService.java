package com.example.metricsproducer.service;

import com.example.metricsproducer.entity.Metric;

import java.util.concurrent.ExecutionException;

public interface KafkaService {

    /**
     * Метод отправляет метрики в топик брокера kafka
     * @param metric
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void send(Metric metric) throws ExecutionException, InterruptedException;
}
