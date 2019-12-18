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

@Controller
public class RoutesController {
    Connection c = null;
    PreparedStatement stmt = null;
    boolean isLogged = false;

    public RoutesController() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://db:5432/" + System.getenv("POSTGRES_DB"),
                            System.getenv("POSTGRES_USER"), System.getenv("POSTGRES_PASSWORD"));
            stmt = c.prepareStatement("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(250) NOT NULL, email VARCHAR(250) NOT NULL, password VARCHAR(250) NOT NULL);");
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    @RequestMapping("/")
    public String home(ModelMap model) {
        if (!isLogged)
            return "login";
        model.addAttribute("App", "Dashboard");
        return "index";
    }

    @RequestMapping("/index")
    public String index(ModelMap model) {
        if (!isLogged)
            return "login";
        model.addAttribute("App", "Dashboard");
        return "index";
    }

    @RequestMapping("/login")
    public String login(ModelMap model) {
        model.addAttribute("App", "Dashboard");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam Map<String, String> formData) {
        String view = LoginController.login(formData, c, stmt);
        if (view.equals("index"))
            isLogged = true;
        return view;
    }

    @RequestMapping(value="/logout")
    public String logout(ModelMap model) {
        model.addAttribute("App", "Dashboard");
        isLogged = false;
        return "login";
    }

    @RequestMapping(value="/register")
    public String register(ModelMap model) {
        model.addAttribute("App", "Dashboard");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@RequestParam Map<String, String> formData) {
        return RegisterController.register(formData, c, stmt);
    }

    @RequestMapping("/profile")
    public String profile(ModelMap model) {
        model.addAttribute("App", "Dashboard");
        if (!isLogged)
            return "login";
        return "profile";
    }
}