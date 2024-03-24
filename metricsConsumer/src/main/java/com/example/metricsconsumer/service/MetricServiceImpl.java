package com.example.metricsconsumer.service;

import com.example.metricsconsumer.domain.Metric;
import com.example.metricsconsumer.repository.MetricsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetricServiceImpl implements MetricService {

    MetricsRepository repository;
    @Override
    public List<Metric> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Metric> findAll(String name) {
        return repository.findAll(name);
    }

    @Override
    public List<Metric> findAll(Date period) {
        return repository.findAll(period);
    }

    @Override
    public List<Metric> findAll(String name, Date period) {
        return repository.findAll(name, period);
    }

    @Override
    public Metric create(Metric metric) {
        return repository.save(metric);
    }
    @Override
    public Optional<Metric> findById(Long id) {
        return repository.findById(id);
    }
}
