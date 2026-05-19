package com.PearlSkin.controller;

import com.PearlSkin.dao.ProductDao;
import com.PearlSkin.dao.ProductDaoImpl;
import com.PearlSkin.entity.Product;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> featuredProducts = productDao.getFeaturedProducts();
        request.setAttribute("featuredProducts", featuredProducts);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request,response);
    }
}
