package com.PearlSkin.controller;

import com.PearlSkin.dao.*;
import com.PearlSkin.entity.Category;
import com.PearlSkin.entity.OrderItem;
import com.PearlSkin.entity.Product;
import com.PearlSkin.utils.ImageUtil;
import com.PearlSkin.utils.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
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

        } else if ("detail".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.getProductById(id);

            request.setAttribute("product", product);

            request.getRequestDispatcher("/WEB-INF/views/product-detail.jsp")
                    .forward(request, response);

        } else if ("adminList".equals(action)) {

            ArrayList<Product> products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.setAttribute("activePage", "product");
            request.getRequestDispatcher("/WEB-INF/views/product-list.jsp")
                    .forward(request, response);

        } else if ("new".equals(action)) {

            CategoryDao categoryDao = new CategoryDaoImpl();
            List<Category> categories = categoryDao.getAllCategories();

            request.setAttribute("categories", categories);
            request.setAttribute("activePage", "product");
            request.getRequestDispatcher("/WEB-INF/views/product-add-edit.jsp")
                    .forward(request, response);

        } else if ("edit".equals(action)) {

            int productId = Integer.parseInt(request.getParameter("productid"));

            Product product = productDao.getProductById(productId);

            CategoryDao categoryDao = new CategoryDaoImpl();
            List<Category> categories = categoryDao.getAllCategories();

            request.setAttribute("categories", categories);
            request.setAttribute("product", product);
            request.setAttribute("activePage", "product");
            request.getRequestDispatcher("/WEB-INF/views/product-add-edit.jsp")
                    .forward(request, response);
        } if ("search".equals(action)) {

            String keyword = request.getParameter("search");

            ArrayList<Product> products;

            if (keyword == null || keyword.trim().isEmpty()) {
                products = productDao.getAllProducts();
            } else {
                products = productDao.getProductsByName(keyword.trim());
            }

            request.setAttribute("products", products);
            request.setAttribute("searchKeyword", keyword);
            request.setAttribute("activePage", "product");
            request.getRequestDispatcher("/WEB-INF/views/product-list.jsp")
                    .forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // ================= ADD PRODUCT =================
        if ("add".equals(action)) {

            String productName = request.getParameter("productName");
            String categoryName = request.getParameter("categoryName");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String skinConcern = request.getParameter("skinConcern");
            String ingredients = request.getParameter("ingredients");
            Date expiryDate = Date.valueOf(request.getParameter("expiryDate"));
            String description = request.getParameter("description");

            String image = ImageUtil.uploadImage(request.getPart("image"));

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

            if (productDao.insertProduct(product)) {
                response.sendRedirect(request.getContextPath() + "/product?action=adminList");
            } else {
                request.setAttribute("error", "Failed to add product.");
                request.getRequestDispatcher("/WEB-INF/views/product-add-edit.jsp")
                        .forward(request, response);
            }
        }

        // ================= EDIT PRODUCT =================
        else if ("edit".equals(action)) {

            int productId = Integer.parseInt(request.getParameter("productid"));
            Product existing = productDao.getProductById(productId);

            String productName = request.getParameter("productName");
            String categoryName = request.getParameter("categoryName");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String skinConcern = request.getParameter("skinConcern");
            String ingredients = request.getParameter("ingredients");
            Date expiryDate = Date.valueOf(request.getParameter("expiryDate"));
            String description = request.getParameter("description");

            String image = existing.getImage();

            Part imagePart = request.getPart("image");
            if (imagePart != null && imagePart.getSize() > 0) {
                String newImage = ImageUtil.uploadImage(imagePart);
                if (newImage != null) {
                    ImageUtil.deleteImage(existing.getImage());
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

            if (productDao.updateProduct(updated)) {
                response.sendRedirect(request.getContextPath() + "/product?action=adminList");
            } else {
                request.setAttribute("error", "Update failed");
                request.setAttribute("product", updated);
                request.getRequestDispatcher("/WEB-INF/views/product-add-edit.jsp")
                        .forward(request, response);
            }
        }

        // ================= DELETE PRODUCT =================
        else if ("delete".equals(action)) {

            int productId = Integer.parseInt(request.getParameter("productid"));
            Product product = productDao.getProductById(productId);

            if (product != null) {
                ImageUtil.deleteImage(product.getImage());
                productDao.deleteProduct(productId);
            }

            response.sendRedirect(request.getContextPath() + "/product?action=adminList");
        }

        // ================= CART ACTIONS =================
        else if ("addToCart".equals(action) || "buyNow".equals(action)) {

            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDao.getProductById(productId);

            if (product == null || product.getStockQuantity() <= 0) {
                response.sendRedirect(request.getContextPath() + "/product?action=detail&id=" + productId);
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
                cart.add(new OrderItem(0, productId, 1, product.getPrice()));
            }

            request.getSession().setAttribute("cart", cart);

            if ("addToCart".equals(action)) {
                response.sendRedirect(request.getContextPath() + "/product?action=list");
            } else {
                response.sendRedirect(request.getContextPath() + "/cart?checkout=true");
            }
        }
    }
}