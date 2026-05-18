package com.PearlSkin.controller;

import com.PearlSkin.dao.UserDao;
import com.PearlSkin.dao.UserDaoImpl;
import com.PearlSkin.entity.User;
import com.PearlSkin.utils.CookieUtil;
import com.PearlSkin.utils.PasswordUtil;
import com.PearlSkin.utils.SessionUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.findByUsername(username);

        if (user == null) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
            return;
        }

        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
            return;
        }

        SessionUtil.setAttribute(request, "user", user);
        CookieUtil.addCookie(response, "username", user.getUsername(), 24*60*60);
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
