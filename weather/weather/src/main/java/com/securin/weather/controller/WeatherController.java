package com.securin.weather.controller;

import com.securin.weather.entity.WeatherData;
import com.securin.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/month/{month}")
    public List<WeatherData> getByMonth(@PathVariable String month) {
        return service.getByMonth(month);
    }

    @GetMapping("/year/{year}")
    public List<WeatherData> getByYear(@PathVariable int year) {
        return service.getByYear(year);
    }

    @GetMapping("/month/{month}/year/{year}")
    public List<WeatherData> getByMonthAndYear(
        @PathVariable String month,
        @PathVariable int year) {
        return service.getByMonthAndYear(month, year);
    }

    @GetMapping("/stats/max")
    public double getMax(
        @RequestParam String month,
        @RequestParam int year) {
        return service.getMaxTemp(month, year);
    }

    @GetMapping("/stats/min")
    public double getMin(
        @RequestParam String month,
        @RequestParam int year) {
        return service.getMinTemp(month, year);
    }

    @GetMapping("/stats/median")
    public double getMedian(
        @RequestParam String month,
        @RequestParam int year) {
        return service.getMedianTemp(month, year);
    }
}