package com.example.metricsproducer.api.v1;

import com.example.metricsproducer.dto.MetricApiDTO;
import com.example.metricsproducer.entity.Metric;
import com.example.metricsproducer.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/metrics")
public class ProducerController {

    MetricService service;

    @Operation(description = "Контроллер принимает запрос на запись новой метрики в БД")
    @PostMapping
    public Metric create(@Valid @RequestBody MetricApiDTO metricApiDTO) {
        return service.createMetricApi(metricApiDTO);
    }


}
