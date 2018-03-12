<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit product</title>
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
                <h3>Edit Product</h3>

                <p style="color: red;">${errorString}</p>

                <c:if test="${not empty code}">
                    <form action="${pageContext.request.contextPath}/pack"
                          method="post">
                        <input type="hidden" name="code" value="${code}"/>
                        <table border="0">
                            <tr>
                                <td>Code</td>
                                <td style="color:red;">${code}</td>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="name" value="${name}"/></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><input type="text" name="price" value="${price}"/></td>
                            </tr>
                            <tr>
                                <td>Mass</td>
                                <td><input type="text" name="mass" value="${mass}"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="hidden" name="newPage" value="changeProduct">
                                    <input type="submit" value="Create"/>
                                </td>
                            </tr>
                        </table>
                    </form>
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