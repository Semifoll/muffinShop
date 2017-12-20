<%--
  Created by IntelliJ IDEA.
  User: semifoll
  Date: 07.12.2017
  Time: 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Muffin Shop error page</title>
    <link href = "${pageContext.request.contextPath}/resources/CSS/page_style.css" rel = "stylesheet" type = "text/css"/>

</head>
<body>
<body>
<div class = "wrapper">
    <!-- 1 -->
    <jsp:include page="_header.jsp"></jsp:include>


    <jsp:include page="_menuline.jsp"></jsp:include>
    <!-- 2 -->
    <div class = "midle_body">
        <!-- 2.1 -->
        <div class = "content row">
            <jsp:include page="_menu_page.jsp"></jsp:include>

            <div class = "c_2 transp">
                <p style="color: red;">${statusString}</p>
                <p style="color: red;">${errorString}</p>
                <p style="color: red;">${errorString2}</p>
            </div>

            <div class = "c_3 transp">CONTENT3

            </div>
        </div>
    </div>

    <jsp:include page="_footer.jsp"></jsp:include>
</div>

</body>
</html>

