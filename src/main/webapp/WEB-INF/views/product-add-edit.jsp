<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
    <jsp:param name="title" value="PearlSkin | ${empty product ? 'Add Product' : 'Edit Product'}" />
    <jsp:param name="cssFile" value="product-add-edit" />
</jsp:include>

<body>

<div class="dashboard">

    <!-- SIDEBAR -->
    <jsp:include page="/WEB-INF/templates/sidebar.jsp" />

    <main class="main-content product-form-page">

        <!-- TOPBAR -->
        <jsp:include page="/WEB-INF/templates/topbar.jsp" />

        <div class="content">

            <!-- PAGE HEADER -->
            <div class="content-header">
                <h3>${empty product ? 'Add Product' : 'Edit Product'}</h3>
                <a href="${pageContext.request.contextPath}/product?action=adminList"
                   class="backBtn">
                    &larr; Back to Products
                </a>
            </div>

            <!-- FORM CARD -->
            <div class="form-card">

                <!-- ERROR / SUCCESS -->
                <c:if test="${not empty error}">
                    <p class="form-msg error"><c:out value="${error}" /></p>
                </c:if>
                <c:if test="${not empty success}">
                    <p class="form-msg success"><c:out value="${success}" /></p>
                </c:if>

                <form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data">

                    <input type="hidden" name="action" value="${empty product ? 'add' : 'edit'}" />
                    <c:if test="${not empty product}">
                        <input type="hidden" name="productid" value="${product.productId}" />
                    </c:if>

                    <!-- Product Name -->
                    <div class="form-row">
                        <label for="productName">Product Name:</label>
                        <input type="text" id="productName" name="productName"
                               placeholder="Product name"
                               value="<c:out value='${product.productName}' default='' />" />
                    </div>

                    <!-- Category -->
                    <div class="form-row">
                        <label for="categoryId">Category:</label>
                        <select id="categoryId" name="categoryId">
                            <option value="">-- Select Category --</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.categoryId}"
                                        <c:if test="${cat.categoryId == product.categoryId}">selected</c:if>>
                                    <c:out value="${cat.categoryName}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Brand -->
                    <div class="form-row">
                        <label for="brand">Brand:</label>
                        <input type="text" id="brand" name="brand"
                               placeholder="Brand"
                               value="<c:out value='${product.brand}' default='' />" />
                    </div>

                    <!-- Price -->
                    <div class="form-row">
                        <label for="price">Price (Rs.):</label>
                        <input type="number" id="price" name="price"
                               placeholder="0.00" step="0.01" min="0"
                               value="<c:out value='${product.price}' default='' />" />
                    </div>

                    <!-- Stock Quantity -->
                    <div class="form-row">
                        <label for="stockQuantity">Stock:</label>
                        <input type="number" id="stockQuantity" name="stockQuantity"
                               placeholder="0" min="0"
                               value="<c:out value='${product.stockQuantity}' default='' />" />
                    </div>

                    <!-- Skin Concern -->
                    <div class="form-row">
                        <label for="skinConcern">Skin Concern:</label>
                        <input type="text" id="skinConcern" name="skinConcern"
                               placeholder="e.g. Acne, Dryness"
                               value="<c:out value='${product.skinConcern}' default='' />" />
                    </div>

                    <!-- Ingredients -->
                    <div class="form-row">
                        <label for="ingredients">Ingredients:</label>
                        <textarea id="ingredients" name="ingredients"
                                  rows="3"
                                  placeholder="List key ingredients"><c:out value="${product.ingredients}" default="" /></textarea>
                    </div>

                    <!-- Expiry Date -->
                    <div class="form-row">
                        <label for="expiryDate">Expiry Date:</label>
                        <input type="date" id="expiryDate" name="expiryDate"
                               value="<fmt:formatDate value='${product.expiryDate}' pattern='yyyy-MM-dd'/>" />
                    </div>

                    <!-- Description -->
                    <div class="form-row">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description"
                                  rows="4"
                                  placeholder="Product description"><c:out value="${product.description}" default="" /></textarea>
                    </div>

                    <!-- Current Image -->
                    <c:if test="${not empty product and not empty product.image}">
                        <div class="form-row">
                            <label>Current Image:</label>
                            <div class="image-preview">
                                <img src="${pageContext.request.contextPath}/uploads/${product.image}"
                                     alt="Current product image" />
                                <p>Upload a new image below to replace, or leave empty to keep the current image.</p>
                            </div>
                        </div>
                    </c:if>

                    <!-- Image Upload -->
                    <div class="form-row">
                        <label for="image">Image:</label>
                        <input type="file" id="image" name="image" accept=".jpg,.jpeg,.png" />
                    </div>

                    <!-- Submit -->
                    <div class="form-actions">
                        <button type="submit">
                            ${empty product ? 'Add Product' : 'Save Changes'}
                        </button>
                    </div>

                </form>

            </div>

        </div>

    </main>

</div>

</body>
</html>
