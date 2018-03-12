<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
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
                <b>Product List</b>
                <b style="color: red;">${errorString}</b> <br>
                <b>${userNickName} your orders:</b> <br>

                <form name="statusform"
                      action="${pageContext.request.contextPath}/pack"
                      method="post">
                    <select name="status">
                        <option selected value="Complete">Complete</option>
                        <option value="Delivered">Delivered</option>
                        <option value="Ready">Ready</option>
                    </select>
                    <input type="hidden" name="newPage" value="clientOrdersPage"/>
                    <input type="submit" value="Change"/>
                </form>
                <form name="statusform"
                      action="${pageContext.request.contextPath}/pack"
                      method="post">
                    <table border="1" cellpadding="5" cellspacing="1" id="tableID">
                        <tr>
                            <th>Code product</th>
                            <th>Name product</th>
                            <th>Price product</th>
                            <th>Mass product</th>
                            <th>Status order</th>
                            <th>Time order</th>
                            <th>Date order</th>
                        </tr>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td>${order.code}</td>
                                <td>${order.name}</td>
                                <td>${order.price}</td>
                                <td>${order.mass}</td>
                                <td>${order.statusOrder}</td>
                                <td>${order.hour}:${order.min}</td>
                                <td>${order.dateString}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>

            <div class="c_3 transp">CONTENT3

            </div>
        </div>
    </div>
    <jsp:include page="../_footer.jsp"></jsp:include>
</div>
</body>
</html>
