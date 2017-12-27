<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <link href="${pageContext.request.contextPath}/resources/CSS/page_style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="wrapper">
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menuline.jsp"></jsp:include>
    <div class="midle_body">
        <!-- 2.1 -->
        <div class="content row">
            <jsp:include page="_menu_page.jsp"></jsp:include>

            <div class="c_2 transp">
                <h3>Hello: ${user.nickName}</h3>

                User "Nick name": <b>${user.nickName}</b>
                <br/>
                First name: <b>${user.firstName}</b>
                <br/>
                Second name: <b>${user.secondName}</b>
                <br/>
                Birth date: <b>${user.birthDate}</b>
                <br/>
                Phone: <b>${user.phoneNumber}</b>
                <br/>
                Rights: <b>${user.accessRights}</b>
                <br/>
            </div>

            <div class="c_3 transp">
                <c:if test="${user.accessRights == 'Admin'}">
                    <a href="accountView">Change users role</a><br/>
                    <a href="productList">Create new product</a>
                </c:if>
                <c:if test="${user.accessRights == 'Worker'}">
                    <a href="orderListWorker">Change orders status</a><br/>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>