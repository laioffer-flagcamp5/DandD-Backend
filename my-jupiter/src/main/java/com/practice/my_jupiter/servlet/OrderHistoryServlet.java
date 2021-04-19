package com.practice.my_jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.my_jupiter.db.MySQLConnection;
import com.practice.my_jupiter.entity.HistoryRequestBody;
import com.practice.my_jupiter.entity.Order;
import com.practice.my_jupiter.entity.ResultResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/order"})
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }

        response.setContentType("application/json");

        String userId = request.getParameter("user_id");

        MySQLConnection connection = new MySQLConnection();
        Set<Order> orders = connection.getOrderHistory(userId);
        connection.close();
        mapper.writeValue(response.getWriter(), orders);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }
        response.setContentType("application/json");
        HistoryRequestBody body = mapper.readValue(request.getReader(), HistoryRequestBody.class);

        MySQLConnection connection = new MySQLConnection();
        connection.setOrderHistory(body.userId, body.order);
        connection.close();

        ResultResponse resultResponse = new ResultResponse("SUCCESS");
        mapper.writeValue(response.getWriter(), resultResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }

        response.setContentType("application/json");
        HistoryRequestBody body = mapper.readValue(request.getReader(), HistoryRequestBody.class);

        MySQLConnection connection = new MySQLConnection();
        connection.unsetFavoriteItems(body.userId, body.order.getId());
        connection.close();

        ResultResponse resultResponse = new ResultResponse("SUCCESS");
        mapper.writeValue(response.getWriter(), resultResponse);
    }

}
