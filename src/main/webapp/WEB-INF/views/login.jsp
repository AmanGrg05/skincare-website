<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="/WEB-INF/templates/head.jsp">
        <jsp:param name="title" value="Pearl Skil - Login" />
        <jsp:param name="cssFile" value="auth" />
    </jsp:include>

<body>
<div class="main-container">

    <!-- LEFT SIDE -->

    <div class="left-section">

        <div class="logo">
            PearlSkin
        </div>

        <form action="LoginServlet" method="post">

            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email">
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password">
            </div>

            <div class="forgot">
                <a href="#">Forgot Password?</a>
            </div>

            <button type="submit" class="login-btn">
                Login
            </button>

        </form>

        <div class="social-icons">

            <div class="icon-box"></div>

            <div class="icon-box"></div>

            <div class="icon-box"></div>

        </div>

        <div class="signup">
            Create New Account
        </div>

    </div>

    <!-- RIGHT SIDE -->

    <div class="right-section">

        <div class="image-box">

            <!-- Add image later -->

        </div>

    </div>

</div>

</body>
</html>