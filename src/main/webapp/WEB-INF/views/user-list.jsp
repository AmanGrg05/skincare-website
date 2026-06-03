<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="/WEB-INF/templates/head.jsp">
  <jsp:param name="title" value="PearlSkin | Users" />
  <jsp:param name="cssFile" value="user-list" />
</jsp:include>

<body>

<div class="dashboard">

  <!-- SIDEBAR -->
  <jsp:include page="/WEB-INF/templates/sidebar.jsp" />

  <main class="main-content user-page">

    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/templates/topbar.jsp" />

    <div class="content">

      <!-- HEADER -->
      <div class="content-header">

        <h3>Users</h3>

        <div class="header-actions">

          <!-- SEARCH -->
          <form action="${pageContext.request.contextPath}/user" method="get">
            <input type="hidden" name="action" value="search" />

            <div class="search-group user-search">
              <input type="text"
                     name="search"
                     placeholder="Search users by name..."
                     value="${searchKeyword}" />

              <button type="submit">Search</button>
            </div>
          </form>

        </div>

      </div>

      <!-- TABLE WRAPPER -->
      <div class="tableWrapper user-table-wrapper">

        <table class="dataTable userTable">

          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Skin Type</th>
            <th>Role</th>
            <th>Registered</th>
            <th>Actions</th>
          </tr>
          </thead>

          <tbody>

          <c:choose>

            <c:when test="${empty users}">
              <tr>
                <td colspan="8" class="emptyRow">
                  No users found.
                </td>
              </tr>
            </c:when>

            <c:otherwise>

              <c:forEach var="user" items="${users}" varStatus="status">

                <tr>

                  <td>${status.count}</td>

                  <td class="user-name">
                      ${user.name}
                  </td>

                  <td>${user.email}</td>

                  <td>${user.phoneNumber}</td>

                  <td>${user.skinType}</td>

                  <td>
                                        <span class="role-badge ${user.isAdmin ? 'admin' : 'customer'}">
                                            <c:choose>
                                              <c:when test="${user.isAdmin}">Admin</c:when>
                                              <c:otherwise>Customer</c:otherwise>
                                            </c:choose>
                                        </span>
                  </td>

                  <td>
                    <fmt:formatDate value="${user.registrationDate}"
                                    pattern="MMM d, yyyy"/>
                  </td>

                  <td class="actionsCell user-actions">

                    <form action="${pageContext.request.contextPath}/user"
                          method="post"
                          class="deleteForm">

                      <input type="hidden" name="action" value="delete"/>
                      <input type="hidden" name="userId" value="${user.userId}"/>

                      <button type="submit"
                              class="deleteBtn user-delete-btn"
                              onclick="return confirm('Delete this user?');">
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