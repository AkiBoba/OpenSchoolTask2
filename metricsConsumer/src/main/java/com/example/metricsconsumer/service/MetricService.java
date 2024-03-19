package com.example.metricsconsumer.service;

import com.example.metricsconsumer.domain.Metric;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricService {

    @KafkaListener(topics = "metrics-topic")
    public void receiveOrder(Metric metric) {
        log.info(metric.toString());
    }

}
