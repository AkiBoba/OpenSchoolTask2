package com.example.metricsproducer.service;

import com.example.metricsproducer.dto.MetricApiDTO;
import com.example.metricsproducer.dto.MetricDTO;
import com.example.metricsproducer.entity.Metric;

import java.util.List;

public interface MetricService {

    Metric create(MetricDTO metricDTO);

    Metric createMetricApi(MetricApiDTO metricApiDTO);

    List<Metric> findLastIn20sec();
}
