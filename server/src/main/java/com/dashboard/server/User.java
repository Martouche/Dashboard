package com.dashboard.server;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Map;

import java.io.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

public class User {

    private static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static void addUser(Map<String, String> data, Connection c, PreparedStatement stmt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.get("password").getBytes(StandardCharsets.UTF_8));

            stmt = c.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?);");
            stmt.setString(1, data.get("name"));
            stmt.setString(2, data.get("email"));
            stmt.setString(3, toHexString(hash));
            stmt.execute();
            System.out.println("User: " + data.get("name") + " just registered");
        } catch (Exception e) {System.out.println(e.getClass().getName()+": " + e.getLocalizedMessage() );}
    }

    public static int logUser(Map<String, String> data, Connection c, PreparedStatement stmt) {

        String check_mail = null;
        String check_name = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.get("password").getBytes(StandardCharsets.UTF_8));

            stmt = c.prepareStatement("SELECT name, email FROM users WHERE email = '" + data.get("email") + "' AND password = '" + toHexString(hash) + "';");
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return 0;
            return 1;
        } catch (Exception e) {System.out.println(e.getClass().getName()+": " + e.getLocalizedMessage() );}
        return 1;
    }
}