package dashboard.WidgetsController;

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


@RestController
public class redditController implements WidgetsController
{
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private final UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;

  public redditController(UserRepository userRepository)
  {
      this.userRepository = userRepository;
  }

  @Override
  public String getData(String input)
  {
    String[] inputParam = input.split(",");
    try {
      URL yahoo = new URL("https://www.reddit.com/r/" + inputParam[0] + "/top/.json");
      URLConnection yc = yahoo.openConnection();
      BufferedReader in = new BufferedReader(
                              new InputStreamReader(
                              yc.getInputStream()));
      String inputLine;
      String prev = "";

      while ((inputLine = in.readLine()) != null) {
        prev = inputLine;
      }
      in.close();
      JsonParser parser = JsonParserFactory.getJsonParser();
      Map<String, Object> services = parser.parseMap(prev);
      String result = "";
      List<Object> children = (List<Object>)((Map<String, Object>)services.get("data")).get("children");
      for (int i = 0; i != children.size(); i++) {
        if (i < new Integer(inputParam[1])) {
          Map<String, Object> data = ((Map<String, Object>)((Map<String, Object>)children.get(i)).get("data"));
          result += data.get("title") + "</br>";
          result += "<img src=\"" + data.get("thumbnail") + "\"></br>";
        }
      }
      return result;
    } catch (Exception e) {
      System.out.printf("Exception  \n");
    }
    return "";
  }


  @PostMapping(path="/reddit")
  public void redditPost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication, @RequestBody String body) throws IOException, ServletException
  {
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    LinkWidgetsUserTable table = new LinkWidgetsUserTable();
    String[] b = body.split("&");
    table.setInput(b[1].split("=")[1] + "," + b[2].split("=")[1]);
    table.setUserId(user.getUserId());
    table.setLinkstate(2);
    table.setWidgetId(new Integer(b[0].split("=")[1]));
    table.setServiceId(0);
    System.out.printf("wid = %d", new Integer(b[0].split("=")[1]));
    linkWidgetsUserRepository.save(table);
    redirectStrategy.sendRedirect(request, response, "/dashboardController");
  }

  @RequestMapping("/reddit")
  public String meteoGet(@RequestParam(name = "input") String param, @RequestParam(name = "index") String index) throws IOException, ServletException
  {
    return getData(param) + "=>/"+index;
  }
}
