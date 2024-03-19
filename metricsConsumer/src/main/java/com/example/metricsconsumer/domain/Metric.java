package com.example.metricsconsumer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metric {
    private Long id;
    private String name;
    private Double value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date date;
}
