<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <div class="logo">
        <h1>PearlSkin</h1>
    </div>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/product">Product</a></li>
            <li><a href="${pageContext.request.contextPath}/static/html/aboutus.html">About us</a></li>
            <li><a href="${pageContext.request.contextPath}/cart">&#128722;</a></li>
        </ul>
    </nav>

    <div class="usersession">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <!-- If user is logged in -->
                <h3>
                    <c:out value="${sessionScope.user.name}" />
                </h3>

                <a href="${pageContext.request.contextPath}/logout"
                   class="log-button"
                   onclick="return confirm('Are you sure you want to logout?');">
                    Logout
                </a>
            </c:when>

            <c:otherwise>
                <!-- If user is NOT logged in -->
                <a href="${pageContext.request.contextPath}/login"
                   class="log-button">
                    Login
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</header>