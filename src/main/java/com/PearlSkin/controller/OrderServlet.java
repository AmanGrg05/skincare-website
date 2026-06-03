package com.PearlSkin.controller;

import com.PearlSkin.dao.OrderDao;
import com.PearlSkin.dao.OrderDaoImpl;
import com.PearlSkin.entity.RecentOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // FULL ORDER LIST PAGE
        if (action == null || action.equals("adminList")) {

            ArrayList<RecentOrder> orders = orderDao.getAllOrders();

            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/WEB-INF/views/order-list.jsp")
                    .forward(request, response);
        }
    }
}