package com.PearlSkin.controller;

import com.PearlSkin.dao.ProductDao;
import com.PearlSkin.dao.ProductDaoImpl;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.entity.Product;
import com.PearlSkin.utils.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            ArrayList<Product> products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/product.jsp")
                    .forward(request, response);

        } else if (action.equals("detail")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.getProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/views/product-detail.jsp")
                    .forward(request, response);

        } else if (action.equals("cart")) {
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("addToCart".equals(action) || "buyNow".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDao.getProductById(productId);

            // stock check
            if (product.getStockQuantity() <= 0) {
                response.sendRedirect(request.getContextPath() + "/product?action=detail&id=" + productId );
                return;
            }

            List<OrderItem> cart = SessionUtil.getCart(request);
            boolean found = false;

            for (OrderItem item : cart) {
                if (item.getProductId() == productId) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                OrderItem item = new OrderItem(0, productId, 1, product.getPrice());
                cart.add(item);
            }

            request.getSession().setAttribute("cart", cart);

            // Redirect after add/buy
            if ("addToCart".equals(action)) {
                response.sendRedirect(request.getContextPath() + "/product?action=cart");
            } else {
                response.sendRedirect(request.getContextPath() + "/product?action=cart&checkout=true");
            }
            return;
        }

        if ("placeOrder".equals(action)) {
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            request.getSession().removeAttribute("cart");
            request.setAttribute("message", "Order placed successfully!");
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                    .forward(request, response);
        }
    }
}