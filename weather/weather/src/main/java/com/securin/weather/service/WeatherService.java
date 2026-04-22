package com.securin.weather.service;

import com.securin.weather.entity.WeatherData;
import com.securin.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository repo;

    // Basic filters
    public List<WeatherData> getByMonth(String month) {
        return repo.findByMonth(month);
    }

    public List<WeatherData> getByYear(int year) {
        return repo.findByYear(year);
    }

    public List<WeatherData> getByMonthAndYear(String month, int year) {
        return repo.findByMonthAndYear(month, year);
    }

    // Max temp for a given month and year
    public double getMaxTemp(String month, int year) {
        List<WeatherData> data = repo.findByMonthAndYear(month, year);
        return data.stream()
                   .mapToDouble(WeatherData::getMeanTemperature)
                   .max()
                   .orElse(0);
    }

    // Min temp for a given month and year
    public double getMinTemp(String month, int year) {
        List<WeatherData> data = repo.findByMonthAndYear(month, year);
        return data.stream()
                   .mapToDouble(WeatherData::getMeanTemperature)
                   .min()
                   .orElse(0);
    }

    // Median temp for a given month and year
    public double getMedianTemp(String month, int year) {
        List<WeatherData> data = repo.findByMonthAndYear(month, year);
        List<Double> temps = data.stream()
                                 .map(WeatherData::getMeanTemperature)
                                 .sorted()
                                 .toList();
        int size = temps.size();
        if (size == 0) return 0;
        if (size % 2 == 1) {
            return temps.get(size / 2);
        } else {
            return (temps.get(size / 2 - 1) + temps.get(size / 2)) / 2.0;
        }
    }
}