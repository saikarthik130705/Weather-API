package com.securin.weather.service;

import com.securin.weather.entity.WeatherData;
import com.securin.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.*;
import java.nio.file.*;

@Service
public class CsvParserService {

    @Autowired
    private WeatherRepository repo;

    @PostConstruct
    public void loadData() throws Exception {
        if (repo.count() > 0) return; // don't reload if data already exists

        InputStream is = getClass().getClassLoader().getResourceAsStream("weather.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        br.readLine(); // skip header row

        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");

            WeatherData w = new WeatherData();
            w.setDate(cols[0]);
            w.setMonth(cols[1]);
            w.setYear(Integer.parseInt(cols[2]));
            w.setMeanTemperature(Double.parseDouble(cols[3]));
            w.setHumidity(Double.parseDouble(cols[4]));
            w.setPressure(Double.parseDouble(cols[5]));

            repo.save(w);
        }
        br.close();
        System.out.println("CSV data loaded successfully.");
    }
}