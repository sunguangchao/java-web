<%--
  Created by IntelliJ IDEA.
  User: 11981
  Date: 2017/8/26
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h2>Product List</h2>
    <a href="<c:url value="/shop?action=viewCart"/>
    ">View Cart</a><br/><br/>
    <%
        Map<Integer, String> products = (Map<Integer, String>) request.getAttribute("products");
        for (int id : products.keySet()){
                %><a href="<c:url value="/shop">
                    <c:param name="action" value="addToCart" />
                    <c:param name="productId" value="<%= Integer.toString(id) %>"/>
                </c:url>"><%= products.get(id) %></a><br /><%
        }
    %>

</body>
</html>
