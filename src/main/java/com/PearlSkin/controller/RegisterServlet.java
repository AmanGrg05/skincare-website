package com.PearlSkin.controller;

import com.PearlSkin.dao.UserDao;
import com.PearlSkin.dao.UserDaoImpl;
import com.PearlSkin.entity.User;
import com.PearlSkin.utils.PasswordUtil;
import com.PearlSkin.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        StringBuilder errors = new StringBuilder();

        if (ValidationUtil.isNullOrEmpty(name)
                || ValidationUtil.isAlphanumericStartingWithLetter(name)
                || name.length() < 4) {
            errors.append("Name should be alphanumeric, starting with letter and be atleast 4 characters. ");
        }
        if (!ValidationUtil.isValidEmail(email)) {
            errors.append("Email should be in valid format. ");
        }
        if (!ValidationUtil.isValidPassword(password)) {
            errors.append("Password must be 7+ characters with uppercase, atleast 1 number and symbol. ");
        }
        if (!ValidationUtil.doPasswordsMatch(password, confirmPassword)) {
            errors.append("Passwords do not match. ");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors.toString().trim());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordUtil.getHashPassword(password);
        User user = new User(name, email, hashedPassword);

        boolean success = userDao.addUser(user);

        if (success) {
            request.setAttribute("error", "Username or email address alreadt exists.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
