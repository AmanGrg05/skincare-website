<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <div class="stat-info">
                            <p class="stat-label">Total Revenue</p>
                            <h3 class="stat-value">Rs. 00,000</h3>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-info">
                            <p class="stat-label">Total Orders</p>
                            <h3 class="stat-value">0,000</h3>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-info">
                            <p class="stat-label">Total Customers</p>
                            <h3 class="stat-value">0,000</h3>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-info">
                            <p class="stat-label">Total Products</p>
                            <h3 class="stat-value">00</h3>
                        </div>
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
                            <tr>
                                <td>#0000</td>
                                <td>Customer Name</td>
                                <td>Product Name</td>
                                <td>Rs. 0,000</td>
                            </tr>
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
                            <li class="product-item">
                                <div class="product-rank">1</div>
                                <div class="product-details">
                                    <p class="product-name">Product Name</p>
                                    <p class="product-sales">000 sold</p>
                                </div>
                                <div class="product-revenue">Rs. 00,000</div>
                            </li>
                        </ul>
                    </section>

                </div>
            </main>
        </div>

    </body>
</html>


