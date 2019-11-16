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
import dashboard.WidgetsController.YoutubeTrendController;

import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


@RestController
public class YoutubeController implements ServicesController {


  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


  @Autowired
  UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;



  public YoutubeController() {
    
  }

  @Override
  public String getData(String input) throws IOException, ServletException
  {
      return "";
  }


  @PostMapping(path="/youtube")
  public void youtubePost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {
  }

  @RequestMapping("/youtube")
  public RedirectView youtubeGet() {
    return YoutubeTrendController.getInstance().Connect();
  }

//  INSERT INTO LINKWIDGETSUSERTABLE(widgetsid, userid, linkstate, input, serviceId) VALUES(3, 1, 0, '0', 2)

}
