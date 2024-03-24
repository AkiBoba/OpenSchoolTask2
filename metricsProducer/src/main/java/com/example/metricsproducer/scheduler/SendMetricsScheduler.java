package com.example.metricsproducer.scheduler;

import com.example.metricsproducer.entity.Metric;
import com.example.metricsproducer.service.KafkaService;
import com.example.metricsproducer.service.MetricService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Класс запускает отправку метрик приложения раз в 20 секунд
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SendMetricsScheduler {

    MetricService metricService;
    KafkaService kafkaService;

    /**
     * Метод получает данные метрик и передает их на отправку по расписанию, первая отправка через 20 сек после запуска приложения и далее через каждые 20 сек.
     */
    @Scheduled(initialDelay = 20000, fixedRate = 20000)
    public void sendMetrics() throws ExecutionException, InterruptedException {
        log.info("Начинаем отправку метрик приложения в брокер");
        List<Metric> metrics = metricService.findLastIn20sec();
        sendMetrics(calculateAverageValueByName(metrics));
        log.info("Отправка метрик завершена");
    }

    /**
     * Метод группирует метрики в словарь имя метрики - среднее значение метрики
     * @param metrics список метрик
     * @return словарь ключ-значение (имя метрики - среднее значение метрики)
     */
    private Map<String, Double> calculateAverageValueByName(List<Metric> metrics) {
        return metrics.stream()
                .collect(Collectors.groupingBy(Metric::getName,
                        Collectors.averagingDouble(Metric::getValue)));
    }

    /**
     * Метод передает каждую метрику и ее среднее значение на отправку
     * @param metricsMap словарь ключ-значение (имя метрики - среднее значение метрики)
     */
    private void sendMetrics(Map<String, Double> metricsMap) throws ExecutionException, InterruptedException {
        for (Map.Entry<String, Double> entry: metricsMap.entrySet()) {
            kafkaService.send(Metric.builder()
                    .name(entry.getKey())
                            .value(entry.getValue())
                            .date(DateTime.now().toDate())
                    .build()
            );
        }
    }

}
