package com.example.metricsproducer.scheduler;

import com.example.metricsproducer.rest.RestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс запускает загрузку метрик приложения раз в 5 секунд
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReceiveMetricsScheduler {

    static String jvmCompilationTime = "http://localhost:8080/actuator/metrics/jvm.compilation.time";
    static String processCpuUsage = "http://localhost:8080/actuator/metrics/process.cpu.usage";
    static String metricsProcessUptime = "http://localhost:8080/actuator/metrics/process.uptime";

    static List<String> urlList = List.of(
            jvmCompilationTime,
            processCpuUsage,
            metricsProcessUptime
        );

    RestService service;

    /**
     * Метод получает данные метрик из системы передает их на сохранение в БД каждые 5 сек.
     */
    @Scheduled(fixedDelay = 5000)
    public void loadMetrics() {
        urlList.forEach(service::getMetrics);
    }
}
