package dashboard.ServicesController;

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


import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.text.ParseException;
import org.json.simple.parser.JSONParser;
import dashboard.ServicesController.ServicesController;

import org.springframework.web.servlet.view.RedirectView;



@RestController
public class facebookController implements ServicesController {


  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();




  public facebookController() {
  }

  @Override
  public String getData(String input) throws IOException, ServletException
  {

      return "";
  }


  @PostMapping(path="/facebook")
  public void meteoPost(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {

//    redirectStrategy.sendRedirect(request, response, "/https://www.facebook.com/dialog/oauth");
  }

  @RequestMapping("/facebook")
  public RedirectView meteoGet() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("https://www.facebook.com/dialog/oauth?client_id=547783625781033&client_secret=3b3005b044615d06ec37fa991ba46812&grant_type=client_credentials");
    return redirectView;
  }
}
