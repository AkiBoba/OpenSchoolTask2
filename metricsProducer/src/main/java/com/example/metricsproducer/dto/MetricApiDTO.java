package com.example.metricsproducer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricApiDTO {

    @NotBlank(message = "Поле имя не заполнено")
    private String name;
    @NotBlank(message = "Поле значение не заполнено")
    private Double value;
}
