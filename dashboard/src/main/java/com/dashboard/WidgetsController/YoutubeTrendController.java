package dashboard.WidgetsController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ExecutionException;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import java.util.Map;
import org.json.simple.JSONValue;
import dashboard.LinkWidgetsUser.LinkWidgetsUserRepository;
import dashboard.LinkWidgetsUser.LinkWidgetsUserTable;
import dashboard.Widgets.WidgetsTable;
import dashboard.Widgets.WidgetsRepository;
import org.springframework.boot.json.*;
import java.net.*;
import java.io.*;
import org.json.simple.*;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import dashboard.WidgetsController.WidgetsController;

import dashboard.User.User;
import dashboard.User.UserRepository;
import dashboard.ServicesController.YoutubeController;




import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.util.Map;

import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;
import dashboard.ServicesController.ServicesController;

import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;

import java.util.concurrent.ExecutionException;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.qmetry.qaf.automation.rest.auth.oauth.OAuthConstants;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import dashboard.User.User;
import dashboard.User.UserRepository;
import dashboard.LinkWidgetsUser.LinkWidgetsUserRepository;
import dashboard.LinkWidgetsUser.LinkWidgetsUserTable;
import dashboard.Widgets.WidgetsTable;
import dashboard.Widgets.WidgetsRepository;


@RestController
public class YoutubeTrendController implements WidgetsController
{
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Autowired
  UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;


  OAuth20Service service;
  OAuth2AccessToken accessToken;


  private static YoutubeTrendController INSTANCE = new YoutubeTrendController();

  private YoutubeTrendController()
  {
    accessToken = new OAuth2AccessToken("");
    service = new ServiceBuilder("https://www.googleapis.com/auth/youtube.readonly")//
      .apiSecret("A0J8bMBAyvXwKjus4uMiV1DS")
      .apiKey("845428365201-prf20a5m96ld8ivteedqeaa6tutmndpi.apps.googleusercontent.com")
      .scope("https://www.googleapis.com/auth/youtube.readonly")
      .callback("http://localhost:8080/t")
      .build(GoogleApi20.instance());
  }

  public static YoutubeTrendController getInstance()
   {   return INSTANCE;
   }

  public RedirectView Connect()
  {
    String authUrl = service.getAuthorizationUrl();
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl(authUrl);
    return redirectView;
  }

  @Override
  public String getData(String input) throws IOException, InterruptedException, ExecutionException
  {
    String[] b = input.split(",");
    System.out.println(b[0]);
    OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/youtube/v3/activities?part=contentDetails&home=true");
    request.addQuerystringParameter("list", "true");
    service.signRequest(accessToken, request);
    Response response = service.execute(request);



    String jsonAll = response.getBody();
    try {
      JsonParser parser = JsonParserFactory.getJsonParser();
      Map<String, Object> services = parser.parseMap(jsonAll);
      List<Object> items = (List<Object>)services.get("items");
      String result = "";
      result += "<a href=\"https://www.youtube.com/watch?v=" + input + "\">";
      result += "<img src=\"https://img.youtube.com/vi/" + input + "/0.jpg\"></a></br></br>";
      return (result);
    } catch (Exception e) {
      System.out.printf("Exception  %s\n", e.getMessage());
    }
    return ("");
  }


  @PostMapping(path="/youtube search")
  public void youtubeTrendPost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication, @RequestBody String body) throws IOException, ServletException
  {
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    LinkWidgetsUserTable table = new LinkWidgetsUserTable();
    String[] b = body.split("&");
    table.setInput(b[1].split("=")[1]);
    table.setUserId(user.getUserId());
    table.setLinkstate(2);
    table.setWidgetId(new Integer(b[0].split("=")[1]));
    table.setServiceId(2);
    linkWidgetsUserRepository.save(table);
    redirectStrategy.sendRedirect(request, response, "/dashboardController");
  }

  @RequestMapping("/youtube search")
  public String youtubeTrendGet(@RequestParam(name = "input") String param, @RequestParam(name = "index") String index) throws IOException, ServletException, InterruptedException, ExecutionException
  {
    return INSTANCE.getData(param) + "=>/"+index;
  }

  @RequestMapping("/t")
  public RedirectView tmp(HttpServletRequest r,
                                      HttpServletResponse res, Authentication authentication, @RequestBody(required=false) String req, @RequestParam(value="code", required=false) String code, @RequestParam(value="scope", required=false) String A) throws IOException, InterruptedException, ExecutionException
  {
    INSTANCE.accessToken = service.getAccessToken(code);
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    LinkWidgetsUserTable table = new LinkWidgetsUserTable();

    table.setInput("0");
    table.setUserId(user.getUserId());
    table.setLinkstate(1);
    table.setWidgetId(3);
    table.setServiceId(2);
    linkWidgetsUserRepository.save(table);


    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://localhost:8080/home");
    return redirectView;
  }
}
