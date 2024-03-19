package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MetricDTO;
import com.example.metricsproducer.entity.Metric;

import java.util.List;

public interface MetricService {

    void create(MetricDTO metricDTO);

    List<Metric> findLastIn20sec();
}
