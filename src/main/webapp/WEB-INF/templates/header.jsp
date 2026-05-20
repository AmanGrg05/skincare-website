
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <div class="logo">
        <h1>PearlSkin</h1>
    </div>
    <nav>
        <ul>
            <li><a href="">Home</a></li>
            <li><a href="">Product</a></li>
            <li><a href="">About us</a></li>
        </ul>
    </nav>

    <div class="usersession">
        <c:choose>

            <!-- If user is logged in -->
            <c:when test="${not empty sessionScope.user}">

                <h3>
                    <c:out value="${sessionScope.user.username}" />
                </h3>

                <a href="${pageContext.request.contextPath}/logout"
                   class="logout"
                   onclick="return confirm('Are you sure you want to logout?');">

                    Logout

                </a>

            </c:when>

            <!-- If user is NOT logged in -->
            <c:otherwise>

                <a href="${pageContext.request.contextPath}/login"
                   class="login-btn">

                    Login

                </a>

            </c:otherwise>

        </c:choose>
    </div>
</header>