package com.example.metricsconsumer.service;

import com.example.metricsconsumer.domain.Metric;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@KafkaListener(topics = "metrics-topic")
public class KafkaListenerService {

    MetricService service;

    @KafkaHandler
    public void handle(Metric metric) {
        service.create(metric);
        log.info("Мы получили сообщение из брокера {}", metric.getDate());
    }
}
