package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long> {

    @Query("FROM Metric m WHERE m.name = :name")
    List<Metric> findAll(@Param("name") String name);

    @Query("FROM Metric m WHERE m.date >= :startDate")
    List<Metric> findAll(@Param("startDate") Date period);

    @Query("FROM Metric m WHERE m.name = :name AND m.date >= :startDate")
    List<Metric> findAll(@Param("name") String name, @Param("startDate") Date period);
}
