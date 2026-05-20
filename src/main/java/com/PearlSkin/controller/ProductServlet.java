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
        }
        // ADMIN PRODUCT LIST
        else if (action.equals("adminList")) {

            ArrayList<Product> products = productDao.getAllProducts();

            request.setAttribute("products", products);

            request.getRequestDispatcher("/WEB-INF/views/product-list.jsp")
                    .forward(request, response);
        }

        // ADD PRODUCT PAGE
        else if (action.equals("new")) {

            request.getRequestDispatcher("/WEB-INF/views/product-add-edit.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = productDao.getProductById(productId);

        //stock check
        if (product.getStockQuantity() <=0) {
            response.sendRedirect("product?action=detail&id=" + productId);
        }

        // Using sessionutil with HttpServlet request
        List<OrderItem> cart = SessionUtil.getCart(request);
        if (cart == null) cart = new ArrayList<>();

        if ("addToCart".equals(action) || "buyNow".equals(action)) {
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
        }

            if (action.equals("buyNow")) {
                response.sendRedirect("order?action=checkout");
            } else {
                response.sendRedirect("product?action=list");
            }
        }
    }

