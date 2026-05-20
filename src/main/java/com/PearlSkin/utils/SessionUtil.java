package com.PearlSkin.utils;

import com.PearlSkin.entity.OrderItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;

public class SessionUtil {

    public static void setAttribute(HttpServletRequest request, String key, Object value){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60);
        session.setAttribute(key, value);
    }

    public static Object getAttribute(HttpServletRequest request, String key){
        HttpSession session = request.getSession(false);
        if(session != null){
            return session.getAttribute(key);
        }
        return null;
    }

    public static void invalidateSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    public static void removeAttribute(HttpServletRequest request, String key){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute(key);
        }
    }

    public static List<OrderItem> getCart(HttpServletRequest request ) {
        HttpSession session = request.getSession(true);
            List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
            if (cart == null){
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }
            return cart;
        }

    }
