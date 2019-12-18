package com.dashboard.server.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenWeatherService {
    private final String key = "3ea8b3dff7faeeace3b8ee11c66e72d0";

    public String Temperature(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key;

        RestTemplate restTemplate = new RestTemplate();
        String temperature = restTemplate.getForObject(url, String.class);

        return temperature;
    }
}