<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
  <jsp:param name="title" value="PearlSkin | Products" />
  <jsp:param name="cssFile" value="product-list" />
</jsp:include>

<body>

<div class="dashboard">

  <!-- SIDEBAR -->
  <jsp:include page="/WEB-INF/templates/sidebar.jsp" />

  <main class="main-content product-page">

    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/templates/topbar.jsp" />

    <div class="content">

      <!-- HEADER ROW: title + search + add button -->
      <div class="content-header">

        <h3>Products</h3>

        <div class="header-actions">

          <form action="${pageContext.request.contextPath}/product" method="get">
            <input type="hidden" name="action" value="search" />
            <div class="search-group">
              <input type="text"
                     name="search"
                     placeholder="Search products..."
                     value="${searchKeyword}" />
              <button type="submit">Search</button>
            </div>
          </form>

          <a href="${pageContext.request.contextPath}/product?action=new"
             class="addProductBtn">
            + Add Product
          </a>

        </div>

      </div>

      <!-- PRODUCT TABLE -->
      <div class="tableWrapper">

        <table class="productTable">

          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Skin Concern</th>
            <th>Expiry</th>
            <th>Actions</th>
          </tr>
          </thead>

          <tbody>

          <c:choose>

            <c:when test="${empty products}">
              <tr>
                <td colspan="9" class="emptyRow">No products found.</td>
              </tr>
            </c:when>

            <c:otherwise>

              <c:forEach var="product" items="${products}" varStatus="status">
                <tr>

                  <td><c:out value="${status.count}" /></td>

                  <td class="productNameCell">
                    <c:out value="${product.productName}" />
                  </td>

                  <td><c:out value="${product.categoryName}" /></td>

                  <td>
                    Rs. <fmt:formatNumber value="${product.price}" type="number" minFractionDigits="2"/>
                  </td>

                  <td><c:out value="${product.stockQuantity}" /></td>

                  <td><c:out value="${product.skinConcern}" /></td>

                  <td>
                    <fmt:formatDate value="${product.expiryDate}" pattern="MMM d, yyyy"/>
                  </td>

                  <td class="actionsCell">

                    <a class="editBtn"
                       href="${pageContext.request.contextPath}/product?action=edit&productid=${product.productId}">
                      Edit
                    </a>

                    <form class="deleteForm"
                          action="${pageContext.request.contextPath}/product"
                          method="post">
                      <input type="hidden" name="action" value="delete" />
                      <input type="hidden" name="productid" value="${product.productId}" />
                      <button class="deleteBtn"
                              type="submit"
                              onclick="return confirm('Are you sure you want to delete this product?');">
                        Delete
                      </button>
                    </form>

                  </td>

                </tr>
              </c:forEach>

            </c:otherwise>

          </c:choose>

          </tbody>

        </table>

      </div>

    </div>

  </main>

</div>

</body>
</html>
