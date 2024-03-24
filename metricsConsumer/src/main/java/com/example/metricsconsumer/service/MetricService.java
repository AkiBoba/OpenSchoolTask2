package com.example.metricsconsumer.service;

import com.example.metricsconsumer.domain.Metric;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MetricService {

    /**
     * Метод получает список метрик из БД
     * @return список объектов класса Metric
     */
    List<Metric> findAll();

    /**
     * Метод получает отфильтрованный список метрик из БД
     * @param name имя метрики
     * @return список объектов класса Metric
     */
    List<Metric> findAll(String name);

    /**
     * Метод получает отфильтрованный список метрик из БД
     * @param period period времени
     * @return список объектов класса Metric
     */
    List<Metric> findAll(Date period);

    /**
     * Метод получает отфильтрованный список метрик из БД
     * @param name имя метрики
     * @param period period времени
     * @return список объектов класса Metric
     */
    List<Metric> findAll(String name, Date period);

    /**
     * Метод сохраняет в БД метрику
     * @param metric DTO метрики полученной из системы
     * @return объект класса  Metric
     */
    Metric create(Metric metric);

    /**
     * Метод находит метрику в БД по id метрики
     * @param id метрики
     * @return объект класса  Metric в обертке  Optional
     */
    Optional<Metric> findById(Long id);
}
