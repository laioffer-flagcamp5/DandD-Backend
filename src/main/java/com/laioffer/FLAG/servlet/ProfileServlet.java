package com.laioffer.FLAG.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.FLAG.entity.ResultResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebServlet(name="ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }
        String userId = request.getParameter("user_id");

        MySQLConnection connection = new MySQLConnection();
        Set<String> profile = connection.getProfile(userId);
        connection.close();
        mapper.writeValue(response.getWriter(), profile);
    }
}
