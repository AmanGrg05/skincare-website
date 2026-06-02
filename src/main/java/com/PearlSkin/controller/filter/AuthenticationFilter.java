package com.PearlSkin.controller.filter;

import com.PearlSkin.entity.User;
import com.PearlSkin.utils.SessionUtil;

import jakarta.servlet.*;
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

        // Allow static resources
        if (path.startsWith("/static/") ||
                path.startsWith("/uploads/") ||
                path.startsWith("/css/") ||
                path.startsWith("/js/") ||
                path.startsWith("/images/")) {

            chain.doFilter(request, response);
            return;
        }

        // Get user from session
        User user = (User) SessionUtil.getAttribute(req, "user");

        boolean isLoggedIn = user != null;
        boolean isAdmin = isLoggedIn && user.isAdmin();

        // Public pages
        boolean isPublic =
                "/".equals(path) ||
                        "/home".equals(path) ||
                        "/login".equals(path) ||
                        "/register".equals(path) ||
                        "/aboutUs".equals(path);

        // Auth pages
        boolean isAuthPage =
                "/login".equals(path) ||
                        "/register".equals(path);

        // Admin-only page
        boolean isDashboard = "/dashboard".equals(path);

        // Block protected pages for guests
        if (!isLoggedIn && !isPublic) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // Prevent logged-in users from accessing login/register
        if (isLoggedIn && isAuthPage) {
            resp.sendRedirect(contextPath + "/home");
            return;
        }

        // ADMIN ONLY ACCESS for dashboard
        if (isDashboard && !isAdmin) {
            resp.sendRedirect(contextPath + "/home");
            return;
        }

        chain.doFilter(request, response);
    }
}