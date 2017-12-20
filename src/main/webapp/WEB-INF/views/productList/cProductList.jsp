<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href = "${pageContext.request.contextPath}/resources/CSS/page_style.css" rel = "stylesheet" type = "text/css"/>

</head>
<body>

<jsp:include page="../_header.jsp"></jsp:include>
<jsp:include page="../_menuline.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>
<p style="color: #4CAF50;">${resultString}</p>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Price</th>
        <th>Mass</th>
        <th>Bue Product</th>

    </tr>
    <c:forEach items="${productList}" var="products" >
        <tr>
            <td>${products.code}</td>
            <td>${products.name}</td>
            <td>${products.price}</td>
            <td>${products.mass}</td>
            <td>
                <a href="buyProduct?code=${products.code}">Buy</a>


            </td>
        </tr>
    </c:forEach>
</table>

<a href="/orderList" >See your order list</a>

<jsp:include page="../_footer.jsp"></jsp:include>

</body>
</html>
