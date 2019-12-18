package com.dashboard.server.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class YoutubeService {
    private final String key = "AIzaSyDNpcyII1P4k7-Dz80GNx-UcNrUh7-TmgY";

    public String Subscribers(String name) {
        String url = "https://www.googleapis.com/youtube/v3/channels?part=statistics&forUsername=" + name + "&fields=items/statistics/subscriberCount&key=" + key;

        RestTemplate restTemplate = new RestTemplate();
        String subscribers = restTemplate.getForObject(url, String.class);

        return subscribers;
    }

    public String LastVideo(String userid) {
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + userid + "&maxResults=1&key=" + key;

        RestTemplate restTemplate = new RestTemplate();
        String lastvideo = restTemplate.getForObject(url, String.class);

        return lastvideo;
    }
}