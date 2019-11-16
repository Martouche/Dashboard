package dashboard.ServicesController;

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
import java.io.IOException;
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


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class googleController implements ServicesController {


  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  OAuth20Service service;




  public googleController() {
    service = new ServiceBuilder("https://www.googleapis.com/auth/calendar")//
  .apiSecret("mcUi07PvZJ03PMEbUMqT-ezF")//G75LZXRFwAHoF8yakx-ixnfQ
  .apiKey("409225018737-8kakkq8mfodrq8bp75299fughpde2of4.apps.googleusercontent.com")
  .scope("https://www.googleapis.com/auth/calendar")
  .callback("http://localhost:8080/tmp")
  .build(GoogleApi20.instance());
  }

  @Override
  public String getData(String input) throws IOException, ServletException
  {

      return "";
  }


  @PostMapping(path="/google")
  public void googlePost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {

//    redirectStrategy.sendRedirect(request, response, "/https://www.facebook.com/dialog/oauth");
  }

  @RequestMapping("/google")
  public RedirectView googleGet() {



    String authUrl = service.getAuthorizationUrl();
    RedirectView redirectView = new RedirectView();
        redirectView.setUrl(authUrl);
//    redirectView.setUrl("http://localhost:8080/tmp");
    return redirectView;
  }



  @RequestMapping("/tmp")
      public String tmp(HttpServletRequest r,
                                          HttpServletResponse res, Authentication authentication, @RequestBody(required=false) String req, @RequestParam(value="code", required=false) String code, @RequestParam(value="scope", required=false) String A) throws IOException, InterruptedException, ExecutionException
                                          {
            System.out.printf("Body => %s and %s   %s\n", req, code, A);
        OAuth2AccessToken accessToken = service.getAccessToken(code);
/*
        Event event = service.events().get('primary', "eventId").execute();

        System.out.println(event.getSummary());
*/

/*
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/calendar/v3/calendars/primary/");
   request.addQuerystringParameter("list", "true");
   service.signRequest(accessToken, request);
   Response response = service.execute(request);

   System.out.println("Got it! Lets see what we found...");
   System.out.println("HTTP RESPONSE: =============");
   System.out.println(response.getCode());

   JSONParser parser = new JSONParser();

   String jsonAll = response.getBody();
   System.out.printf("AAA -> %s\n", jsonAll);
   try {
     Object obj = parser.parse(jsonAll);


     JSONObject jsonAllObj = (JSONObject) obj;

     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     String jsonItem = gson.toJson(jsonAllObj);

     System.out.println(jsonItem);
     System.out.println("END RESPONSE ===============");

     JSONObject rj = (JSONObject) JSONValue.parse(response.getBody());
     JSONArray contents = (JSONArray) rj.get("contents");

     for (int i=0; i<contents.size(); i++) {
         JSONObject item = (JSONObject) contents.get(i);
         String path = (String) item.get("path");
         System.out.println(" - " + path);
     }
   } catch (Exception e) {
     return "azd";
   }

/*
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
//        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v1/calendar?alt=json");

        service.signRequest(accessToken, request);

        Response response = service.execute(request);
        System.out.printf("Bite => %s \n", response.getBody());


*/

        return "azd";
      }





}
