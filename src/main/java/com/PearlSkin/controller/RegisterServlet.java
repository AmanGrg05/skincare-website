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
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        String PasswordHash = request.getParameter("PasswordHash");
        String PhoneNumber = request.getParameter("PhoneNumber");
        String confirmPassword = request.getParameter("confirmPassword");

        StringBuilder errors = new StringBuilder();

        if (ValidationUtil.isNullOrEmpty(Name)
                || !ValidationUtil.isAlphanumericStartingWithLetter(Name)
                || Name.length() < 4) {
            errors.append("Name should be alphanumeric, starting with letter and be atleast 4 characters. ");
        }
        if (!ValidationUtil.isValidEmail(Email)) {
            errors.append("Email should be in valid format. ");
        }
        if (!ValidationUtil.isValidPhoneNumber(PhoneNumber)) {
            errors.append("Phone number should be in valid format. ");
        }
        if (!ValidationUtil.isValidPassword(PasswordHash)) {
            errors.append("Password must be 7+ characters with uppercase, atleast 1 number and symbol. ");
        }
        if (!ValidationUtil.doPasswordsMatch(PasswordHash, confirmPassword)) {
            errors.append("Passwords do not match. ");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("error", errors.toString().trim());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordUtil.getHashPassword(PasswordHash);
        User user = new User(Name, Email, hashedPassword, PhoneNumber);

        boolean success = userDao.addUser(user);

        if (!success) {
            request.setAttribute("error", "Username or email address already exists.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
