package com.PearlSkin.controller;

import com.PearlSkin.dao.UserDao;
import com.PearlSkin.dao.UserDaoImpl;
import com.PearlSkin.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("adminList")) {

            List<User> users = userDao.getAllUsers();

            request.setAttribute("users", users);

            request.getRequestDispatcher("/WEB-INF/views/user-list.jsp")
                    .forward(request, response);
        }else if ("search".equals(action)) {

            String keyword = request.getParameter("search");

            List<User> users;

            if (keyword == null || keyword.trim().isEmpty()) {
                users = userDao.getAllUsers();
            } else {
                users = userDao.searchUsers(keyword.trim());
            }

            request.setAttribute("users", users);
            request.setAttribute("searchKeyword", keyword);

            request.getRequestDispatcher("/WEB-INF/views/user-list.jsp")
                    .forward(request, response);
        }
    }
}