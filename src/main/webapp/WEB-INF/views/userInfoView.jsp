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
                    <form action="${pageContext.request.contextPath}/pack"
                          method="post">
                        <button  name="newPage" value="pageAccountView" type="submit">
                            Change users role</button><br/>
                        <button  name="newPage" value="createProductPage" type="submit">
                            Create new product</button><br/>
                        <button  name="newPage" value="wOrders" type="submit">
                            Change orders status</button><br/>
                    </form>
                </c:if>
                <c:if test="${user.accessRights == 'Worker'}">
                    <form action="${pageContext.request.contextPath}/pack"
                          method="post">
                        <input type="hidden" name="newPage" value="wOrders">
                        <input type="submit" value="Change orders status"/>
                    </form>
                </c:if>
                <form action="${pageContext.request.contextPath}/pack"
                      method="post">
                    <button  name="newPage" value="loginOut" type="submit">
                        LogOut</button><br/>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>