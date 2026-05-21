<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="PearlSkin | Home" />
    <jsp:param name="cssFile" value="index" />
</jsp:include>

<body>
    <jsp:include page="/WEB-INF/templates/header.jsp" />

    <main>
        <div class="hero">
            <div class="left-section">
                <div class="left-section-wrapper">
                    <h4>Nourish your skin.</h4>
                    <br>
                    <h4>Elevate your glow</h4>
                    <p>Discover skincare that brings out
                        <br>your natural radiance</p>
                    <a href="" class="button">Shop now &rarr;</a>
                </div>
            </div>
            <div class="right-section">
                <img src="${pageContext.request.contextPath}/static/images/main-section.png" alt="">
            </div>
        </div>
        <div class="section-title">Our Best Sellers</div>
            <section class="Best-Seller">
                <div class="best-seller-left-section">
                    <div class="best-seller-text">
                        <h3>Glow with care</h3>
                        <p>Gentle formulas for every skin type</p>
                        <a href="" class="button">BUY NOW</a>
                    </div>
                    <div class="best-seller-image">
                        <img src="${pageContext.request.contextPath}/static/images/best-seller-left-section.png" alt="" height="300px" width="300px">
                    </div>
                </div>
                <div class="best-seller-right-section">
                    <div class="best-seller-text">
                        <h3>Your glow start here</h3>
                        <p>Clean. Safe. Effective</p>
                        <a href="" class="button">BUY NOW</a>
                    </div>
                    <div class="best-seller-image">
                        <img src="${pageContext.request.contextPath}/static/images/best-seller-right-section.png" alt="" height="250px" width="300px">
                    </div>
                </div>
            </section>
            <section class="promotional-banner-section">
                <div class="promotional-banner-left">
                    <img src="${pageContext.request.contextPath}/static/images/leaf.png" alt="">
                    <p>CLEAN INGREDIENTS <br> Safe for you and <br> the planet</p>
                </div>
                <div class="promotional-banner-middle">

                    <h3>Healthy skin is <br> always in</h3>
                    <p>Simple. Nature. Effective.</p>
                    <a href="" class="button">EXPLORE COLLECTION</a>
                </div>
                <div class="promotional-banner-right">
                    <img src="${pageContext.request.contextPath}/static/images/test.png" alt="">
                    <p> DERMATOLOGICALLY <br> TESTED <br> Trusted by the experts, <br> loved by skin
                    </p>
                </div>
            </section>

            <div class="section-title">
                <span>Featured products</span>
            </div>

        <section class="featured-products">

            <c:forEach var="product" items="${featuredProducts}">

                <div class="product-card">

                    <!-- IMAGE SECTION (matches .product-img CSS) -->
                    <div class="product-img">
                        <img src="${pageContext.request.contextPath}/uploads/${product.image} "
                             alt="<c:out value='${product.productName}'/>">
                    </div>

                    <!-- PRODUCT INFO -->
                    <div class="product-info">

                        <h4>
                            <c:out value="${product.productName}" />
                        </h4>

                        <p class="product-sub">
                            <c:out value="${product.description}" />
                        </p>

                        <p class="product-price">
                            Rs. <c:out value="${product.price}" />
                        </p>

                        <button class="add-to-cart">
                            ADD TO CART &#128722;
                        </button>

                    </div>

                </div>

            </c:forEach>

        </section>
    </main>
    <%@ include file="/WEB-INF/templates/footer.html" %>
</body>

</html>