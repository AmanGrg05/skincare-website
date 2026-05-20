    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="/WEB-INF/templates/head.jsp">
        <jsp:param name="title" value="PearlSkin — Login" />
        <jsp:param name="cssFile" value="login" />
    </jsp:include>

<body>

<div class="login-page">

    <!-- LEFT SIDE -->
    <div class="login-left">

        <div class="login-header">
            <h1>Pearl Skin</h1>
        </div>

        <div class="login-form-container">

            <form action="${pageContext.request.contextPath}/login" method="post">

                <h2>Login</h2>

                <c:if test="${not empty error}">
                    <p class="error">
                        <c:out value="${error}" />
                    </p>
                </c:if>

                <div class="input-group">
                    <label>Email</label>
                    <input type="text"
                           name="username"
                           placeholder="Enter your email"
                           value="<c:out value='${param.username}' default='${cookie.username.value}' />"
                           required />
                </div>

                <div class="input-group">
                    <label>Password</label>
                    <input type="password"
                           name="password"
                           placeholder="Enter your password"
                           required />
                </div>

                <button type="submit">Login</button>

                <p class="register-link">
                    Don't have an account?
                    <a href="${pageContext.request.contextPath}/register">
                        Register
                    </a>
                </p>
            </form>
        </div>
    </div>

    <!-- RIGHT SIDE -->
    <div class="login-right">
        <img src="${pageContext.request.contextPath}/static/images/main-section.png"
             alt="Login Illustration" />
    </div>
</div>
</body>
</html>