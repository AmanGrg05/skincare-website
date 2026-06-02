package com.PearlSkin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // ================= PLACE ORDER =================
        if ("placeOrder".equals(action)) {

            request.getSession().removeAttribute("cart");

            request.setAttribute("message", "Order placed successfully!");

            request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                    .forward(request, response);
        }
    }
}