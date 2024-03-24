package com.example.metricsproducer.service;

import com.example.metricsproducer.entity.Metric;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KafkaServiceImpl implements KafkaService {

    KafkaTemplate<String, Metric> kafkaTemplate;

    @Override
    public void send(Metric metric) {
            CompletableFuture<SendResult<String, Metric>> future = kafkaTemplate.send("metrics-topic",
                    metric.getName(), metric);
            future.whenComplete((result, exeption) -> {
                if (exeption != null) {
                    log.error("При отправке метрики получена ошибка {}", exeption.getMessage());
                }
                log.info("Метрика {}, отправлена в топик {}, в партицию {}, офсет №{}", metric.getName(),
                        result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            });

    }
}
