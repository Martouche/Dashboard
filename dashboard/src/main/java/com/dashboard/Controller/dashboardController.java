package dashboard.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import dashboard.LinkWidgetsUser.LinkWidgetsUserRepository;
import dashboard.LinkWidgetsUser.LinkWidgetsUserTable;
import dashboard.Widgets.WidgetsTable;
import dashboard.WidgetsController.meteoController;
import dashboard.User.User;
import dashboard.User.UserRepository;
import java.util.List;
import dashboard.Widgets.WidgetsRepository;
import dashboard.WidgetsController.WidgetsController;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

import dashboard.WidgetsController.redditController;
import dashboard.WidgetsController.YoutubeTrendController;


@RestController
public class dashboardController {

  private final UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;

  @Autowired
  private Map<String, WidgetsController> mapWidgets;

  @PostConstruct
  public void init() {
    System.out.printf("INIt\n");
    mapWidgets.put("meteo", new meteoController(userRepository));
    mapWidgets.put("reddit", new redditController(userRepository));
    mapWidgets.put("youtube search", YoutubeTrendController.getInstance());
  }

  public dashboardController(UserRepository userRepository) {
      this.userRepository = userRepository;
  }

  @RequestMapping(value = "/dashboardController", method = RequestMethod.GET)
  public ModelAndView index(Authentication authentication)
  {
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    ModelAndView modelAndView = new ModelAndView();

    List<LinkWidgetsUserTable> list = linkWidgetsUserRepository.findAll();
    List<List<String>> listString = new ArrayList<List<String>>();
    int n = 0;
    for (LinkWidgetsUserTable i : list) {
      if (i.getUserId() == user.getUserId() && i.getLinkstate() == 2) {
        try {
          WidgetsTable al = widgetsRepository.getOne(i.getWidgetId());

          List<String> t = new ArrayList<String>();
          System.out.printf("al.getLabel  = %s\n", al.getLabel());
          t.add(mapWidgets.get(al.getLabel()).getData(i.getInput()));
          System.out.printf("ok\n");
          t.add(al.getLabel());

          t.add(Integer.toString(n));
          t.add(i.getInput());
          listString.add(t);
        } catch (Exception e) {
          System.out.printf("Exception  %s\n", e.getMessage());
        }
        n++;
      }
    }

    List<String> po = new ArrayList<String>();
    for (List<String> i : listString) {
      po.add(i.stream()
        .map(a -> String.valueOf(a))
        .collect(Collectors.joining(",,")));
    }

    modelAndView.addObject("message", listString);
    modelAndView.addObject("data", po.stream()
      .map(la -> String.valueOf(la))
      .collect(Collectors.joining("->")));
    modelAndView.setViewName("dashboard.html");
    return modelAndView;
  }
}
