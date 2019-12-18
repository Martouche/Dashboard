package com.dashboard.server.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@RestController
public class TwitchService {
    private final String id = "qf4ofh1kezv6eko4vmozlwmivcgb8v";

    public String Games(int number) {
        String url = "https://api.twitch.tv/helix/games/top?first=" + number;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", id);
        HttpEntity<TwitchService> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        String games = result.getBody();

        return games;
    }

    public String Streams(int number) {
        String url = "https://api.twitch.tv/helix/streams/?first=" + number;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", id);
        HttpEntity<TwitchService> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        String streams = result.getBody();

        return streams;
    }
}