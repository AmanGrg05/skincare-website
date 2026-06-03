package com.PearlSkin.controller;

import com.PearlSkin.dao.OrderDao;
import com.PearlSkin.dao.OrderDaoImpl;
import com.PearlSkin.entity.Order;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<OrderItem> cart = SessionUtil.getCart(request);
        request.setAttribute("cart", cart);

        if ("true".equals(request.getParameter("success"))) {
            request.setAttribute("message", "Order placed successfully!");
        }

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!"placeOrder".equals(request.getParameter("action"))) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        HttpSession session = request.getSession();


        Integer userId = (Integer) session.getAttribute("userId");
        List<OrderItem> cart = SessionUtil.getCart(request);


        if (userId == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/product?action=list");
            return;
        }

        String address = request.getParameter("address");

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("cart", cart);
            request.setAttribute("error", "Shipping address required");
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                    .forward(request, response);
            return;
        }

        //  Calculate total
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : cart) {
            total = total.add(item.getSubtotal());
        }

        //  Build order
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setShippingAddress(address.trim());
        order.setItems(new ArrayList<>(cart));

        int orderId = orderDao.createOrder(order);

        if (orderId > 0) {
            cart.clear();
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/cart?success=true");
        } else {
            request.setAttribute("cart", cart);
            request.setAttribute("error", "Order failed. Try again.");
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                    .forward(request, response);
        }
    }
}