package com.securin.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.securin.weather.entity.WeatherData;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByMonth(String month);
    List<WeatherData> findByYear(int year);
    List<WeatherData> findByMonthAndYear(String month, int year);
}
