package com.example.metricsproducer.service;

import com.example.metricsproducer.entity.Metric;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KafkaServiceImpl implements KafkaService {

    KafkaTemplate<String, Metric> kafkaTemplate;

    @Override
    public void send(Metric metric) {
        kafkaTemplate.send("metrics-topic", metric);
    }
}
