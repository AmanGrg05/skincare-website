<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.PearlSkin.entity.Product" %>

<html>
<head>
<style>
    body { font-family: "Georgia", serif;
        margin: 0;
        background: #f7f7f7;
    }

    .details-wrapper {
        display: flex;
        gap:40px;
        padding: 40px 50px;
        background: #FFFFFF;
    }

    .details-left { width: 45%; }

    .details-main {
        width: 100%;
        border: 1px solid #cccccc;
    }

    .details-right {width: 55%}
    .desc { margin:15px 0; }
    .info-block {
    border-top:1px solid #cccccc;
    padding: 10px 0; }

    .btn-row {margin-top: 20px;}
    .btn-dark { background: beige;
        color: black;
        border: none;
        padding: 10px 20px;
        margin-right: 10px;
        cursor: pointer;
    }
    .out-stock {
        color:black;
        font-weight: bold;
        margin-top: 10px; }

    /* Responsive */
    @media (max-width: 768px) {
        .details-wrapper {
            flex-direction: column;
            padding: 20px;
        }
        .details-left, .details-right {
            width: 100%;
        }
        .details-main {
            max-height: 360px;
        }
        .btn-dark {
            width: 100%;
            margin-bottom: 10px;
        }
        .btn-row {
            display: block;
        }
    }
</style>
</head>

    <jsp:include page="/WEB-INF/templates/head.jsp">
        <jsp:param name="title" value="Pearl Skin | Product"/>
        <jsp:param name="cssFile" value="product"/>
    </jsp:include>

<body>
    <jsp:include page="/WEB-INF/templates/header.jsp" />

    <div class="details-wrapper">
        <div class="details-left">
            <img src="${pageContext.request.contextPath}/uploads/${product.image}" class="details-main">
        </div>

        <div class="details-right">
            <h2>${product.productName}</h2>
            <p class="desc">${product.description}</p>
            <div class="price-box"></div>

            <div class="info-block">
                Skin Concern: ${product.skinConcern}
            </div>
            <div class="info-block">
                Ingredients: ${product.ingredients}
            </div>

            <c:choose>
                <c:when test="${product.stockQuantity <=0}">
                    <p class="out-stock">Out of Stock</p>
                </c:when>
                <c:otherwise>
                    <div class="btn-row">

                        <form action="${pageContext.request.contextPath}/product" method="post">
                            <input type="hidden" name="action" value="addToCart">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <button class="btn-dark">Add To Cart</button>
                        </form>

                        <form action="${pageContext.request.contextPath}/product" method="post">
                            <input type="hidden" name="action" value="buyNow">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <button class="btn-dark">Buy Now</button>
                        </form>

                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <%@ include file="/WEB-INF/templates/footer.html" %>
</body>
</html>