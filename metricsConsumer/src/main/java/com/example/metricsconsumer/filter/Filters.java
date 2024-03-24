package com.example.metricsconsumer.filter;

import com.example.metricsconsumer.domain.Metric;
import com.example.metricsconsumer.service.MetricService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Filters {

    MetricService service;

    /**
     * Метод получает список метрик и возвращает отфильтрованный результат в соответствии с переданными параметрами фильтрации
     * @param name имя метрики
     * @param period период за который надо отобрать метрики в миллисекундах
     * @return список метрик
     */
    public List<Metric> checkFiltersParams(String name, Integer period) {
        if (name != null && period != null) {
            return service.findAll(name, new Date(System.currentTimeMillis() - period));
        }
        if (period != null) {
            return service.findAll(new Date(System.currentTimeMillis() - period));
        }
        if (name != null) {
            return service.findAll(name);
        }
        return service.findAll();
    }

}
