<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 5/20/2026
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/templates/head.jsp">
<jsp:param name="title" value="PearlSkin | User-List" />
<jsp:param name="cssFile" value="user-list" />
</jsp:include>
<body>
<div class="dashboard">

  <!-- SIDEBAR -->
  <jsp:include page="/WEB-INF/templates/sidebar.jsp" />

  <!-- MAIN CONTENT -->
  <main class="main-content">

    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/templates/topbar.jsp" />
  </main>
</div>
</body>
</html>
