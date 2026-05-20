<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="Learning Log | Product"/>
    <jsp:param name="cssFile" value="product"/>
</jsp:include>

<body>
    <jsp:include page="/WEB-INF/templates/header.jsp" />


        <main class="content">
        <h2 class="page-title">Our Products</h2>

        <div class="product-grid">
            <c:forEach var="p" items="${products}" end="5">
                <div class="product-card">
                    <a href="product?action=details&id=${p.id}">
                        <img src="${p.image}" alt="${p.name}">
                    </a>

                    <h3>${p.name}</h3>
                    <p class="price">${p.price}</p>

                    <form action="product" method="get">
                        <input type="hidden" name="action" value="details">
                        <input type="hidden" name="id" value="${p.id}">
                        <button class="btn-outline">View Details</button>
                    </form>

                    <form action="product" method="post">
                        <input type="hidden" name="action" value="addTocart">
                        <input type="hidden" name="productId" value="${p.id}">
                        <button class="btn-primary">Add To Cart </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </main>

         <%@ include file="/WEB-INF/templates/footer.html" %>
</body>
</html>