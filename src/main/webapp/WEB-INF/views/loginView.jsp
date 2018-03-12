<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
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
                <h3>Login Page</h3>
                <p style="color: red;">${errorString}</p>

                <form action="${pageContext.request.contextPath}/pack"
                        method="post">
                    <table border="0">
                        <tr>
                            <td>User nick name</td>
                            <td><input type="text" name="nickName" value="${user.nickName}"/></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="text" name="password" value="${user.password}"/></td>
                        </tr>
                        <tr>
                            <td>Remember me</td>
                            <td><input type="checkbox" name="rememberMe" value="Y"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="hidden" name="newPage" value="authorization">
                                <input type="submit" value="Submit"/>
                                <input type="reset" value="Clear">

                            </td>
                        </tr>
                    </table>
                </form>

                <p style="color:#32cd32;">User nick name: tom, password: tom001 or jerry/jerry001</p>

            </div>

            <div class="c_3 transp">CONTENT3

            </div>
        </div>
    </div>

    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>