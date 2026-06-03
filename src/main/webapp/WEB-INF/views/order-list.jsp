<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="PearlSkin | Orders" />
    <jsp:param name="cssFile" value="order-list" />
</jsp:include>

<body>

<div class="dashboard">

    <jsp:include page="/WEB-INF/templates/sidebar.jsp" />

    <main class="main-content order-page">

        <jsp:include page="/WEB-INF/templates/topbar.jsp" />

        <div class="content">

            <section class="recent-orders">

                <div class="section-header">
                    <h3>Order List</h3>
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

                        <c:when test="${empty orders}">
                            <tr>
                                <td colspan="4" class="emptyRow">
                                    No orders found
                                </td>
                            </tr>
                        </c:when>

                        <c:otherwise>

                            <c:forEach var="order" items="${orders}">

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

        </div>

    </main>

</div>

</body>
</html>