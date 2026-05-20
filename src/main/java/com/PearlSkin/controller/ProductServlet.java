package com.PearlSkin.controller;

import com.PearlSkin.dao.ProductDao;
import com.PearlSkin.dao.ProductDaoImpl;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet ("/product")
public class ProductServlet extends HttpServlet {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action==null || action.equals("list")) {
            ArrayList<Product> products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
                    .forward(request, response);

        } else if (action.equals("details")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.getProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
                    .forward(request, response);
        }else if (action.equals("adminList")) {

            ArrayList<Product> products = productDao.getAllProducts();

            request.setAttribute("products", products);

            request.getRequestDispatcher("/WEB-INF/views/product-list.jsp")
                    .forward(request, response);
        }else if (action.equals("new")) {
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

        HttpSession session = request.getSession();
        Map<Integer, OrderItem> cart =
                (Map<Integer, OrderItem>) session.getAttribute("cart");

        if (cart == null) cart = new HashMap<>();

        if (action.equals("addToCart") || action.equals("buyNow")){
            if(cart.containsKey(productId)){
                OrderItem item = cart.get(productId);
                item.setQuantity(item.getQuantity() +1);
        } else {
                OrderItem item = new OrderItem(0,productId,1,product.getPrice());
                item.setProductId(productId);
                item.setQuantity(1);
                item.setUnitPrice(product.getPrice());
                cart.put(productId, item);
            }
            session.setAttribute("cart", cart);

            if (action.equals("buyNow")) {
                response.sendRedirect("order?action=checkout");
            } else {
                response.sendRedirect("product?action=list");
            }
        }
    }
}
