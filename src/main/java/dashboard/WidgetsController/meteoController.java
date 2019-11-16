package dashboard.WidgetsController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import java.util.Map;
import dashboard.LinkWidgetsUser.LinkWidgetsUserRepository;
import dashboard.LinkWidgetsUser.LinkWidgetsUserTable;
import dashboard.Widgets.WidgetsTable;
import dashboard.Widgets.WidgetsRepository;

import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import dashboard.WidgetsController.WidgetsController;

import dashboard.User.User;
import dashboard.User.UserRepository;


@RestController
public class meteoController implements WidgetsController {


  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private final UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;


  public meteoController(UserRepository userRepository) {
      this.userRepository = userRepository;
  }

  @Override
  public String getData(String input) throws IOException, ServletException
  {
      URL yahoo = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + input + "&appid=78448aa8b9b57178ecd4d97f3adcc1bc");
      URLConnection yc = yahoo.openConnection();
      BufferedReader in = new BufferedReader(
                              new InputStreamReader(
                              yc.getInputStream()));
      String inputLine;
      String prev = "";

      while ((inputLine = in.readLine()) != null) {
          prev = inputLine;
      }
      JSONParser parser = new JSONParser();
      try {
        JSONObject jsonObject = (JSONObject) parser.parse(prev);
        in.close();
        String tmp = String.valueOf(Float.parseFloat(((JSONObject)jsonObject.get("main")).get("temp").toString()) - 273.15);
        System.out.println(tmp);
        return (input + " " + tmp.substring(0, tmp.indexOf('.') + 3) + "°");
      } catch (Exception e) {
        System.out.printf("Exception  \n");
      }
      return "";
  }


  @PostMapping(path="/meteo")
  public void meteoPost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication, @RequestBody String body) throws IOException, ServletException
  {
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    LinkWidgetsUserTable table = new LinkWidgetsUserTable();

    String[] tmp = body.split("&");

    table.setInput(tmp[1].split("=")[1]);
    table.setUserId(user.getUserId());
    table.setLinkstate(2);
    table.setWidgetId(new Integer(tmp[0].split("=")[1]));
    table.setServiceId(0);
    linkWidgetsUserRepository.save(table);
    redirectStrategy.sendRedirect(request, response, "/dashboardController");
  }

  @RequestMapping("/meteo")
  public String meteoGet(@RequestParam(name = "input") String param, @RequestParam(name = "index") String index) throws IOException, ServletException
  {

    return getData(param) + "=>/"+index;
  }
}
