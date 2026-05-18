package com.PearlSkin.controller;

import com.PearlSkin.utils.CookieUtil;
import com.PearlSkin.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUtil.invalidateSession(request);
        CookieUtil.deleteCookie(response, "username");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
