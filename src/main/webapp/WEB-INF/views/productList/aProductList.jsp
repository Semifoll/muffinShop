<%--
  Created by IntelliJ IDEA.
  User: semifoll
  Date: 07.12.2017
  Time: 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="${pageContext.request.contextPath}/resources/CSS/page_style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="wrapper">
    <jsp:include page="../_header.jsp"></jsp:include>
    <jsp:include page="../_menuline.jsp"></jsp:include>
    <div class="midle_body">
        <!-- 2.1 -->
        <div class="content row">
            <jsp:include page="../_menu_page.jsp"></jsp:include>

            <div class="c_2 transp">
                <h3>Product List</h3>

                <p style="color: red;">${errorString}</p>

                <table border="1" cellpadding="5" cellspacing="1">
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>average_mass</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${productList}" var="products">
                        <tr>
                            <td>${products.code}</td>
                            <td>${products.name}</td>
                            <td>${products.price}</td>
                            <td>${products.mass}</td>
                            <td>
                                <a href="editProduct?code=${products.code}">Edit</a>
                            </td>
                            <td>
                                <a href="deleteProduct?code=${products.code}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${user.accessRights == 'Admin'}">
                    <a href="createProduct">Create Product</a>
                </c:if>
            </div>

            <div class="c_3 transp">CONTENT3

            </div>
        </div>
    </div>

    <jsp:include page="../_footer.jsp"></jsp:include>
</div>

</body>
</html>
