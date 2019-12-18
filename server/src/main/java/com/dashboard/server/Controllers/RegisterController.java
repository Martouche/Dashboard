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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

public class RegisterController {

    public static String register(Map<String, String> formData, Connection c, PreparedStatement stmt) {
        if (formData != null) {

            String name = formData.get("name");
            String email = formData.get("email");
            String password = formData.get("password");

            if (name != null && email != null && password != null)
                User.addUser(formData, c, stmt);
        }
        return "login";
    }
}