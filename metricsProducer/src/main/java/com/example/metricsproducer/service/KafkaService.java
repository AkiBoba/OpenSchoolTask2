package com.example.metricsproducer.service;

import com.example.metricsproducer.entity.Metric;

public interface KafkaService {

    void send(Metric metric);
}
