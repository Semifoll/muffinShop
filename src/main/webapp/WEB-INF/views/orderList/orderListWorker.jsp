<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <link href = "${pageContext.request.contextPath}/resources/CSS/page_style.css" rel = "stylesheet" type = "text/css"/>

</head>
<body>
<div class ="wrapper">
    <jsp:include page="../_header.jsp"></jsp:include>
    <jsp:include page="../_menuline.jsp"></jsp:include>
    <div class = "midle_body">
        <!-- 2.1 -->
        <div class = "content row">
            <jsp:include page="../_menu_page.jsp"></jsp:include>

            <div class = "c_2 transp">
                <h3>Product List</h3>

                <p style="color: red;">${errorString}</p> <br>
                <h4>${orderList[0].userNickName}</h4> <br>
                <br>
                <form name = "statusform"
                      action="/orderListWorker"
                      method="post">
                    <select name = "statusOrder">
                        <option selected value = "Complete">Complete</option>
                        <option value = "Delivered">Delivered</option>
                        <option value = "Ready">Ready</option>
                    </select>
                    <input type="submit" value="submit"/>
                </form>

                <table border="1" cellpadding="5" cellspacing="1"  id="tableID">
                    <tr>
                        <th>Code order</th>
                        <th>Name product</th>
                        <th>Price product</th>

                        <th>Status order</th>
                        <th>Time order</th>
                        <th>Date order</th>
                    </tr>
                    <c:forEach items="${orderList}" var="order" >
                        <tr>
                            <td>${order.codOrder}</td>
                            <td>${order.name}</td>
                            <td>${order.price}</td>
                            <td>${order.statusOrder}</td>
                            <td>${order.hour}:${order.min}</td>
                            <td>${order.dateString}</td>
                        </tr>
                    </c:forEach>
                </table>
                <form name = "formChange" action="${pageContext.request.contextPath}/changeOrderStatus" method="post">
                    <select name = "codOrder">
                        <c:forEach items="${orderList}" var="order" >
                            <option><c:out value="${order.codOrder}" /></option>
                        </c:forEach>
                    </select>
                    <select name = "statusOrder">
                        <option value = "Complete">Complete</option>
                        <option value = "Delivered">Delivered</option>
                        <option value = "Ready">Ready</option>
                    </select>
                    <input type="submit" value= "Submit" />
                </form>
            </div>

            <div class = "c_3 transp">CONTENT3

            </div>
        </div>
    </div>
<jsp:include page="../_footer.jsp"></jsp:include>
</div>
</body>
</html>
