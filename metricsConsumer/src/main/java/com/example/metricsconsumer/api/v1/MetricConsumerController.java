package com.example.metricsconsumer.api.v1;

import com.example.metricsconsumer.domain.Metric;
import com.example.metricsconsumer.filter.Filters;
import com.example.metricsconsumer.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1")
public class MetricConsumerController {

    MetricService service;
    Filters filters;

    @Operation(description = "Контроллер принимает запрос на получение списка всех продуктов, допустимы параметры для фильтрации результата")
    @GetMapping("/metrics")
    public ResponseEntity<List<Metric>> findFiltered(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Integer period) {
        return new ResponseEntity<>(filters.checkFiltersParams(name, period), HttpStatus.OK);
    }

    @Operation(description = "Контроллер принимает запрос на получение метрики из БД")
    @GetMapping("/metrics/{id}")
    public Metric findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow();

    }


}
