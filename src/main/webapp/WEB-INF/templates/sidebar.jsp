<aside class="sidebar">
    <div class="sidebar-logo">
        <h1>PearlSkin</h1>
        <p>Admin Panel</p>
    </div>
    <nav class="sidebar-nav">
        <ul>
            <li class="${activePage == 'dashboard' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            </li>

            <li class="${activePage == 'product' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/product?action=adminList">Products</a>
            </li>

            <li class="${activePage == 'order' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/order?action=adminList">Orders</a>
            </li>

            <li class="${activePage == 'user' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/user?action=adminList">Customers</a>
            </li>
        </ul>
    </nav>
    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/logout" class="logout-btn" onclick="return confirm('Are you sure you want to logout?');">Logout</a>
    </div>
</aside>