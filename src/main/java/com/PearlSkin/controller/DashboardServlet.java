package com.PearlSkin.controller;

import com.PearlSkin.dao.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("totalRevenue", orderItemDao.getTotalRevenue());
        request.setAttribute("totalOrders", orderItemDao.getTotalOrders());
        request.setAttribute("totalCustomers", userDao.countCustomers());
        request.setAttribute("totalProducts", productDao.countProducts());

        request.setAttribute("recentOrders", orderItemDao.getRecentOrders(5));
        request.setAttribute("topProducts", productDao.getTopProducts(5));
        request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request,response);
    }
}

