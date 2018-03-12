<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Muffin Shop</title>
    <link href="${pageContext.request.contextPath}/resources/CSS/page_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <!-- 1 -->
    <jsp:include page="_header.jsp"></jsp:include>

    <jsp:include page="_menuline.jsp"></jsp:include>
    <!-- 2 -->
    <div class="midle_body">
        <!-- 2.1 -->
        <div class="content row">
            <jsp:include page="_menu_page.jsp"></jsp:include>

            <div class="c_2 transp">
                <form name="usersForm">
                    <table border="1" cellpadding="5" cellspacing="1">
                        <tr>
                            <th>Code</th>
                            <th>Nick name</th>
                            <th>Name</th>
                            <th>Birth date</th>
                            <th>Phone number</th>
                            <th>Role</th>
                        </tr>
                        <c:forEach items="${usersList}" var="user">
                            <tr>
                                <td>${user.cod}</td>
                                <td>${user.nickName}</td>
                                <td>${user.firstName} ${user.secondName}</td>
                                <td>${user.birthDate}</td>
                                <td>${user.phoneNumber}</td>
                                <td>${user.accessRights}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
                <br/>
                <br/>

                <form name="formChange" action="${pageContext.request.contextPath}/pack"
                            method="post">
                    <select name="codUser">
                        <c:forEach items="${usersList}" var="user">
                            <option><c:out value="${user.cod}"/></option>
                        </c:forEach>
                    </select>
                    <select name="role">
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.codRole}"><c:out value="${role.name}"/></option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="newPage" value="changeUserRole">
                    <input type="submit" value="Change User Role"/>
                </form>
            </div>
            <div class="c_3 transp">    CONTENT3
            </div>
        </div>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>
