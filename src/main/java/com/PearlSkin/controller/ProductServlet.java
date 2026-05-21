package com.PearlSkin.controller;

import com.PearlSkin.dao.OrderDao;
import com.PearlSkin.dao.OrderDaoImpl;
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
import com.PearlSkin.utils.ImageUtil;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.math.BigDecimal;
import java.sql.Date;

@MultipartConfig
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private final ProductDao productDao = new ProductDaoImpl();

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
        }else if (action.equals("edit")) {

            int productId = Integer.parseInt(
                    request.getParameter("productid"));

            Product product =
                    productDao.getProductById(productId);

            request.setAttribute("product", product);

            request.getRequestDispatcher(
                            "/WEB-INF/views/product-add-edit.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        //Add product
        if ("add".equals(action)) {

            String productName =
                    request.getParameter("productName");

            String categoryName =
                    request.getParameter("categoryName");

            BigDecimal price =
                    new BigDecimal(
                            request.getParameter("price"));

            int stockQuantity =
                    Integer.parseInt(
                            request.getParameter("stockQuantity"));

            String skinConcern =
                    request.getParameter("skinConcern");

            String ingredients =
                    request.getParameter("ingredients");

            Date expiryDate =
                    Date.valueOf(
                            request.getParameter("expiryDate"));

            String description =
                    request.getParameter("description");

            Part imagePart =
                    request.getPart("image");

            String image =
                    ImageUtil.uploadImage(imagePart);

            Product product = new Product(
                    categoryName,
                    productName,
                    price,
                    stockQuantity,
                    skinConcern,
                    ingredients,
                    expiryDate,
                    description,
                    image
            );

            boolean success =
                    productDao.insertProduct(product);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/product?action=adminList");

            } else {

                request.setAttribute(
                        "error",
                        "Failed to add product.");

                request.getRequestDispatcher(
                                "/WEB-INF/views/product-add-edit.jsp")
                        .forward(request, response);
            }
        }
        //Edit product
        else if ("edit".equals(action)) {

            int productId =
                    Integer.parseInt(
                            request.getParameter("productid"));

            Product existing =
                    productDao.getProductById(productId);

            String productName =
                    request.getParameter("productName");

            String categoryName =
                    request.getParameter("categoryName");

            BigDecimal price =
                    new BigDecimal(
                            request.getParameter("price"));

            int stockQuantity =
                    Integer.parseInt(
                            request.getParameter("stockQuantity"));

            String skinConcern =
                    request.getParameter("skinConcern");

            String ingredients =
                    request.getParameter("ingredients");

            Date expiryDate =
                    Date.valueOf(
                            request.getParameter("expiryDate"));

            String description =
                    request.getParameter("description");

            Part imagePart =
                    request.getPart("image");

            String image =
                    existing.getImage();

            if (imagePart != null
                    && imagePart.getSize() > 0) {

                String newImage =
                        ImageUtil.uploadImage(imagePart);

                if (newImage != null) {

                    ImageUtil.deleteImage(
                            existing.getImage());

                    image = newImage;
                }
            }

            Product updated = new Product(
                    productId,
                    categoryName,
                    productName,
                    price,
                    stockQuantity,
                    skinConcern,
                    ingredients,
                    expiryDate,
                    description,
                    image
            );

            boolean success =
                    productDao.updateProduct(updated);

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/product?action=adminList");

            } else {

                request.setAttribute(
                        "error",
                        "Failed to update product.");

                request.setAttribute(
                        "product",
                        updated);

                request.getRequestDispatcher(
                                "/WEB-INF/views/product-add-edit.jsp")
                        .forward(request, response);
            }
        }
        //delete product
        else if ("delete".equals(action)) {

            int productId =
                    Integer.parseInt(
                            request.getParameter("productid"));

            Product product =
                    productDao.getProductById(productId);

            if (product != null) {

                ImageUtil.deleteImage(
                        product.getImage());

                productDao.deleteProduct(productId);
            }

            response.sendRedirect(
                    request.getContextPath()
                            + "/product?action=adminList");
        }


        else if ("addToCart".equals(action) || "buyNow".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDao.getProductById(productId);

            // stock check
            if (product.getStockQuantity() <= 0 || product == null) {
                response.sendRedirect(request.getContextPath()
                        + "/product?action=detail&id=" + productId
                );
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
}