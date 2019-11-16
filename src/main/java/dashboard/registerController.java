package dashboard;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import dashboard.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import java.util.Map;
import dashboard.User.User;

@RestController
public class registerController {


  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private UserRepository userRepository;

  public registerController() {
  }

  @PostMapping(path="/register")
  public void registerPost(HttpServletRequest request,
                                      HttpServletResponse response, @RequestBody String body) throws IOException, ServletException
  {
    User userToAdd = new User();
    String[] tmp = body.split("&");
    userToAdd.setUsername(tmp[0].split("=")[1]);
    userToAdd.setPassword(tmp[1].split("=")[1]);
    userRepository.save(userToAdd);

    redirectStrategy.sendRedirect(request, response, "/login.html");
  }

  @RequestMapping("/register")
  public String registerGet() {
    return "logi";
  }
}
