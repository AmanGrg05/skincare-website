<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="Pearl Skin | Product"/>
    <jsp:param name="cssFile" value="product"/>
</jsp:include>

<body>
    <jsp:include page="/WEB-INF/templates/header.jsp" />


        <main class="content">
        <h2 class="page-title">Our Products</h2>

            <div class="product-grid">
                <c:if test="${not empty products}">
                <c:forEach var="p" items="${products}" >
                    <div class="product-card">

                        <a href="product?action=detail&id=${p.productId}">
                            <img src="${pageContext.request.contextPath}/static/images/${p.image}"
                                 alt="${p.productName}">
                        </a>

                        <h3>${p.productName}</h3>

                        <p class="price">${p.price}</p>

                        <a href="product?action=detail&id=${p.productId}" class="btn-outline">
                            View Details
                        </a>

                        <form action="product" method="post">
                            <input type="hidden" name="action" value="addToCart">
                            <input type="hidden" name="productId" value="${p.productId}">
                            <button class="btn-primary">Add To Cart </button>
                        </form>
                    </div>
                </c:forEach>
                </c:if>
            </div>
    </main>

         <%@ include file="/WEB-INF/templates/footer.html" %>
</body>
</html>