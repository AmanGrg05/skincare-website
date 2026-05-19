package com.PearlSkin.controller.filter;

import com.PearlSkin.utils.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        if (path.startsWith("/static/")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = SessionUtil.getAttribute(req, "user") != null;

        boolean isAdmin = SessionUtil.getAttribute(req, "isAdmin") != null;

        boolean isUser = "/".equals(path)
                || "/home".equals(path)
                || "/login".equals(path)
                || "/aboutUs".equals(path)
                || "/register".equals(path);

        boolean isAuthPage = "/login".equals(path)
                || "/register".equals(path);

        boolean isAdminPage = "/admin".equals(path);

        //Not logged in
        if (!isLoggedIn && !isUser){
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        //Already logged in
        if (isLoggedIn && isAuthPage) {
            resp.sendRedirect(contextPath + "/dashboard");
            return;
        }

        if (!isAdmin && isAdminPage) {
            resp.sendRedirect(contextPath + "/dashboard");
            return;
        }
        chain.doFilter(request, response);
    }
}
