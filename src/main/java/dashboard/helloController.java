package dashboard;

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
import java.util.List;
import dashboard.Widgets.WidgetsRepository;
import java.util.Iterator;
import dashboard.User.User;
import dashboard.User.UserService;
import dashboard.User.UserRepository;
import dashboard.Services.ServicesRepository;
import dashboard.Services.ServicesTable;


@RestController
public class helloController {
  @Autowired
  UserService userDetailsService;

  private final UserRepository userRepository;

  @Autowired
  LinkWidgetsUserRepository linkWidgetsUserRepository;

  @Autowired
  WidgetsRepository widgetsRepository;

  @Autowired
  ServicesRepository servicesRepository;


  public helloController(UserRepository userRepository) {
      this.userRepository = userRepository;
  }

  private class widgetTmp
  {
    String label;
    Integer NbrOfInput;
    Integer id;
    public widgetTmp(String label, Integer NbrOfInput, Integer id)
    {
      this.label = label;
      this.NbrOfInput = NbrOfInput;
      this.id = id;
    }

    public String getLabel()
    {
      return label;
    }

    public Integer getNbrOfInput()
    {
      return NbrOfInput;
    }

    public int getId()
    {
      return id;
    }
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView index(Authentication authentication)
  {
    User user = userRepository.findUserWithName(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    ModelAndView modelAndView = new ModelAndView();
    List<widgetTmp> toppings = new ArrayList<widgetTmp>();

    List<LinkWidgetsUserTable> list = linkWidgetsUserRepository.findAll();

    for (LinkWidgetsUserTable temp : list) {
      if (temp.getUserId() == user.getUserId() && temp.getLinkstate() == 1) {
            WidgetsTable al = widgetsRepository.getOne(temp.getWidgetId());
            widgetTmp tmp = new widgetTmp(al.getLabel(), al.getNbrOfInput(), temp.getWidgetId());

		        toppings.add(tmp);
        }
     }

     List<ServicesTable> servicesList = servicesRepository.findAll();
     List<String> ServicesString = new ArrayList<String>();

     for (ServicesTable temp : servicesList) {
       ServicesString.add(temp.getLabel());
      }


    modelAndView.addObject("message", toppings);
    modelAndView.addObject("servicesList", servicesList);
    modelAndView.setViewName("hello.html");
    return modelAndView;
  }
}
