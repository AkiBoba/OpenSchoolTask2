package com.example.metricsproducer.rest;

import com.example.metricsproducer.dto.MetricDTO;
import com.example.metricsproducer.service.MetricService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RestService {
    RestTemplate restTemplate;

    MetricService metricService;

    static HttpHeaders headers = new HttpHeaders();

    public void getMetrics(String url) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        metricService.create(restTemplate.getForObject(url, MetricDTO.class));
    }

}
