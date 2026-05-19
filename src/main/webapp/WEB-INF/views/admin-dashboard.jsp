<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="/WEB-INF/templates/head.jsp">
        <jsp:param name="title" value="PearlSkin | Dashboard" />
        <jsp:param name="cssFile" value="admin-dashboard" />
    </jsp:include>
    <body>
        <div id="dashboard">
            <main class="main-content">
                <!-- SIDEBAR -->
                <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
                <!-- TOPBAR -->
                <jsp:include page="/WEB-INF/templates/topbar.jsp" />

                <!-- STATS CARDS -->
                <section class="stats-cards">
                    <div class="stat-card">
                        <p class="stat-label">Total Revenue</p>
                        <h3 class="stat-value">
                            Rs. <c:out value="${totalRevenue}" default="0"/>
                        </h3>
                    </div>

                    <div class="stat-card">
                        <p class="stat-label">Total Orders</p>
                        <h3 class="stat-value">
                            <c:out value="${totalOrders}" default="0"/>
                        </h3>
                    </div>

                    <div class="stat-card">
                        <p class="stat-label">Total Customers</p>
                        <h3 class="stat-value">
                            <c:out value="${totalCustomers}" default="0"/>
                        </h3>
                    </div>

                    <div class="stat-card">
                        <p class="stat-label">Total Products</p>
                        <h3 class="stat-value">
                            <c:out value="${totalProducts}" default="0"/>
                        </h3>
                    </div>

                </section>

                <!-- BOTTOM SECTION -->
                <div class="bottom-section">

                    <!-- RECENT ORDERS -->
                    <section class="recent-orders">
                        <div class="section-header">
                            <h3>Recent Orders</h3>
                            <a href="#" class="view-all">View all</a>
                        </div>

                        <table class="orders-table">

                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Product</th>
                                <th>Amount</th>
                            </tr>
                            </thead>

                            <tbody>

                            <c:choose>
                                <c:when test="${empty recentOrders}">
                                    <tr>
                                        <td colspan="4">No recent orders found</td>
                                    </tr>
                                </c:when>

                                <c:otherwise>
                                    <c:forEach var="order" items="${recentOrders}">

                                        <tr>
                                            <td>#<c:out value="${order.orderId}" /></td>
                                            <td><c:out value="${order.customerName}" /></td>
                                            <td><c:out value="${order.productName}" /></td>
                                            <td>Rs. <c:out value="${order.amount}" /></td>
                                        </tr>

                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            </tbody>

                        </table>

                    </section>

                    <!-- TOP PRODUCTS -->
                    <section class="top-products">
                        <div class="section-header">
                            <h3>Top Products</h3>
                            <a href="#" class="view-all">View all</a>
                        </div>
                        <ul class="product-list">
                            <c:choose>
                                <c:when test="${empty topProducts}">
                                    <li>No products found</li>
                                </c:when>

                                <c:otherwise>

                                    <c:forEach var="product" items="${topProducts}" varStatus="status">

                                        <li class="product-item">

                                            <div class="product-rank">
                                                <c:out value="${status.index + 1}" />
                                            </div>

                                            <div class="product-details">
                                                <p class="product-name">
                                                    <c:out value="${product.productName}" />
                                                </p>

                                                <p class="product-sales">
                                                    <c:out value="${product.totalSold}" /> sold
                                                </p>
                                            </div>

                                            <div class="product-revenue">
                                                Rs. <c:out value="${product.revenue}" />
                                            </div>

                                        </li>

                                    </c:forEach>

                                </c:otherwise>
                            </c:choose>

                        </ul>
                    </section>

                </div>
            </main>
        </div>

    </body>
</html>


