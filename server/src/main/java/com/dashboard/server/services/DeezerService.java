package com.dashboard.server.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeezerService {
    public String Songs(String artist) {
        String url = "https://api.deezer.com/search?q=" + artist;

        RestTemplate restTemplate = new RestTemplate();
        String songs = restTemplate.getForObject(url, String.class);

        return songs;
    }
}
