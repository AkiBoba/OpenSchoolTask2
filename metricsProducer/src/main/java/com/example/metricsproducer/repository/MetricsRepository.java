package com.example.metricsproducer.repository;

import com.example.metricsproducer.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long> {

    @Query("SELECT m FROM Metric m WHERE m.date >= :startDate")
    List<Metric> findMetricsForLast20Seconds(@Param("startDate") Date date);
}
