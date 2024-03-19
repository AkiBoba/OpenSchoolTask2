package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MetricDTO;
import com.example.metricsproducer.entity.Metric;
import com.example.metricsproducer.repository.MetricsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetricServiceImpl implements MetricService {

    MetricsRepository repository;

    /**
     * Метод сохраняет в БД метрику
     * @param metricDTO DTO метрики полученной из системы
     */
    @Override
    public void create(MetricDTO metricDTO) {
        repository.save(build(metricDTO));
    }

    /**
     * Метод получает из БД все метрик за последние 20 секунд.
     * @return список метрик
     */
    @Override
    public List<Metric> findLastIn20sec() {
        return repository.findMetricsForLast20Seconds(new Date(System.currentTimeMillis() - 20000));
    }

    /**
     * Метод преобразует объект MetricDTO в объект Metric
     * @param metricDTO объект MetricDTO
     * @return объект Metric
     */
    private Metric build(MetricDTO metricDTO) {
        return Metric.builder()
                .name(metricDTO.getName())
                .value(metricDTO.getMeasurements().get(0).getValue())
                .date(DateTime.now().toDate())
                .build();
    }
}
