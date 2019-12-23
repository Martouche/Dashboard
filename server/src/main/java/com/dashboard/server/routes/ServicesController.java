package com.dashboard.server.routes;

import java.sql.*;
import java.util.Map;

import java.io.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dashboard.server.User;
import com.dashboard.server.services.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesController {
    OpenWeatherService OpenWeatherService = new OpenWeatherService();
    YoutubeService YoutubeService = new YoutubeService();
    TwitchService TwitchService = new TwitchService();
    DeezerService DeezerService = new DeezerService();
    MovieService MovieService = new MovieService();
    ChuckNorrisService ChuckNorrisService = new ChuckNorrisService();
    CocktailService CocktailService = new CocktailService();
    ActualiteService ActualiteService = new ActualiteService();
    LyricsService LyricsService = new LyricsService();
    

    @RequestMapping("/service/weather/temperature")
    public String Temperature(@RequestParam("value") String value) {
        return OpenWeatherService.Temperature(value);
    }

    @RequestMapping("/service/deezer/artist")
    public String Artist(@RequestParam("value") String value) {
        return DeezerService.Songs(value);
    }

    @RequestMapping("/service/movie/movie")
    public String Movie(@RequestParam("value") String value) { return MovieService.Movie(value); }

    @RequestMapping("/service/youtube/subscribers")
    public String Subscribers(@RequestParam("value") String value) {
        return YoutubeService.Subscribers(value);
    }

    @RequestMapping("/service/youtube/lastvideo")
    public String LastVideo(@RequestParam("userid") String userid) {
        return YoutubeService.LastVideo(userid);
    }

    @RequestMapping("/service/twitch/games")
    public String Games(@RequestParam("value") int value) {
        return TwitchService.Games(value);
    }

    @RequestMapping("/service/twitch/streams")
    public String Streams(@RequestParam("value") int value) {
        return TwitchService.Streams(value);
    }

    @RequestMapping("/service/cocktails/cocktail")
    public String Cocktail(@RequestParam("value") String value) { return CocktailService.Cocktail(value); }

    @RequestMapping("/service/news/new")
    public String News(@RequestParam("value") String value) { return ActualiteService.News(value); }

    @RequestMapping("/service/search/lyrics")
    public String Lyrics(@RequestParam("artist") String artist, @RequestParam("title") String title ) { return LyricsService.Lyrics(artist, title); }

    @RequestMapping("/service/chucknorris/facts")
    public String Fact() { return ChuckNorrisService.Fact(); }
}
