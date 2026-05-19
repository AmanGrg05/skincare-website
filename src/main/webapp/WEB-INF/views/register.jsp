<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="PearlSkin — Register" />
    <jsp:param name="cssFile" value="register" />
</jsp:include>

<body>

<div class="register-page">

    <!-- LEFT SECTION -->
    <div class="register-left">

        <div class="register-header">
            <h1>Pearl Skin</h1>
        </div>

        <div class="register-form-container">

            <form action="${pageContext.request.contextPath}/register" method="post">

                <h2>Create Account</h2>

                <c:if test="${not empty error}">
                    <p class="error">
                        <c:out value="${error}" />
                    </p>
                </c:if>

                <!-- NAME -->
                <div class="input-group">
                    <label>Name</label>
                    <input type="text"
                           name="name"
                           placeholder="Enter your name"
                           value="<c:out value='${param.name}' default='' />"
                           required />
                </div>

                <!-- EMAIL -->
                <div class="input-group">
                    <label>Email</label>
                    <input type="email"
                           name="email"
                           placeholder="Enter your email"
                           value="<c:out value='${param.email}' default='' />"
                           required />
                </div>

                <!-- PHONE NUMBER -->
                <div class="input-group">
                    <label>Phone Number</label>
                    <input type="tel"
                           name="phoneNumber"
                           placeholder="Enter your phone number"
                           value="<c:out value='${param.phoneNumber}' default='' />"
                           required />
                </div>

                <!-- PASSWORD -->
                <div class="input-group">
                    <label>Password</label>
                    <input type="password"
                           name="password"
                           placeholder="Enter your password"
                           required />
                </div>

                <!-- CONFIRM PASSWORD -->
                <div class="input-group">
                    <label>Confirm Password</label>
                    <input type="password"
                           name="confirmPassword"
                           placeholder="Confirm your password"
                           required />
                </div>

                <button type="submit">Register</button>

                <div class="divider">
                    <span>OR</span>
                </div>

                <p class="login-link">
                    Already have an account?
                    <a href="${pageContext.request.contextPath}/login">
                        Log in
                    </a>
                </p>

            </form>

        </div>

    </div>

    <!-- RIGHT SECTION -->
    <div class="register-right">
        <img src="${pageContext.request.contextPath}/static/images/best-seller-left-section.png"
             alt="Register Illustration" />
    </div>

</div>

</body>
</html>