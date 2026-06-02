<%@ page import="java.util.*" %>
<%@ page import="com.PearlSkin.entity.OrderItem" %>
<%@ page import="com.PearlSkin.entity.Product" %>
<%@ page import="com.PearlSkin.dao.ProductDaoImpl" %>

<html>
<head>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f7f7f7;
            color: #222222;
        }

        .cart-container {
            display: flex;
            gap: 30px;
            padding: 40px;
            max-width: 1200px;
            margin: 0 auto;
        }

        .cart-left {
            flex: 2;
            background: #FFFFFF;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .cart-title {
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: 600;
        }

        .cart-item {
            display: flex;
            gap: 20px;
            padding: 15px 0;
            border-bottom: 1px solid #eee;
        }

        .cart-item img {
            width: 110px;
            height: 110px;
            object-fit: cover;
            border-radius: 8px;
        }

        .cart-info h2 {
            font-size: 18px;
            margin-bottom: 6px;
        }

        .cart-info p {
            font-size: 14px;
            color: #555555;
            margin-bottom: 8px;
        }

        .cart-price {
            font-weight: bold;
            margin-bottom: 6px;
            color: #000000;
        }

        .qty {
            font-size: 14px;
            color: #444444;
        }

        .checkout-box {
            flex: 1;
            background: #FFFFFF;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            height: fit-content;
        }
        .checkout-box h2 {
            margin-bottom: 15px;
            font-size: 20px;
        }
        .summary-row {
            display: flex;
            justify-content: space-between;
            margin: 10px 0 20px;
            font-size: 16px;
            font-weight: 600;
        }
        .checkout-box input,
        .checkout-box textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 12px;
            border: 1px solid #cccccc;
            border-radius: 6px;
            font-size: 14px;
        }
        .place-btn {
            width: 100%;
            padding: 12px;
            background: #000000;
            color: #FFFFFF;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
        }

        .place-btn:hover {
            background: #333;
        }

        .success {
            background: #e6ffe6;
            color: #1f7a1f;
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 15px;
            font-size: 14px;
        }
        /*Responsive */
        @media (max-width: 600px) {
            .cart-title {
                font-size: 20px;
            }

            .checkout-box h2 {
                font-size: 18px;
            }

            .summary-row {
                font-size: 14px;
            }

            .place-btn {
                font-size: 14px;
            }
        }
    </style>
</head>

<body>
<div class="cart-container">
    <div class="cart-left">
        <h1 class="cart-title">Shopping Cart </h1>
        <%
            List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
            ProductDaoImpl dao = new ProductDaoImpl();
            double grandTotal = 0;

            if (cart != null && !cart.isEmpty()) {
                for (OrderItem item : cart) {
                    Product p = dao.getProductById(item.getProductId());
                    double total = p.getPrice().doubleValue() * item.getQuantity();
                    grandTotal += total;
        %>
        <div class="cart-item">
            <img src="<%=request.getContextPath()%>/uploads/<%=p.getImage()%>">
            <div class="cart-info">
                <h2><%=p.getProductName()%></h2>
                <p><%=p.getDescription()%></p>
                <div class="cart-price">Rs <%=p.getPrice()%></div>
                <div class="qty">Quantity: <%=item.getQuantity()%></div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p>Your cart is empty.</p>
        <%
            }
        %>

    </div>

    <!-- ====== CHECKOUT ======= -->
    <div class="checkout-box">
        <%
            if(request.getAttribute("message") != null){
        %>
        <div class="success"><%=request.getAttribute("message")%></div>
        <%
            }
        %>

        <h2>Checkout</h2>
        <div class="summary-row">
            <span>Total</span>
            <strong>Rs <%=grandTotal%></strong>
        </div>

        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="placeOrder">
            <input type="text" name="fullname" placeholder="Full Name" required>
            <input type="text" name="phone" placeholder="Phone Number" required>
            <textarea name="address" rows="4" placeholder="Shipping Address" required></textarea>
            <button class="place-btn">Pay to confirm order</button>
        </form>
    </div>
</div>
</body>
</html>