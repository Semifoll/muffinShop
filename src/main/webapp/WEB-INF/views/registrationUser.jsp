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

            <div class="c_2 transp" style="margin-left: 2px">
                <h3>Write your :</h3><br/>
                <p style="color: red;">${errorString}</p>
                <form action="${pageContext.request.contextPath}/pack"
                      method="post">
                    <table border="1" cellpadding="5" cellspacing="1" align="left">
                        <tr>
                            <th>
                                <label for="uNickName">User nick name: </label>
                            </th>
                            <th>
                                <input type="text" id="uNickName" name="nickName"
                                       placeholder="All one word" size="20">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="uPassword">Password number:</label>
                            </th>
                            <th>
                                <input id="uPassword" name="password" type="password">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="uFirstName">User first name: </label>
                            </th>
                            <th>
                                <input type="text" id="uFirstName" name="firstName"
                                       placeholder="All one word" size="50">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="uSecondName">User second name: </label>
                            </th>
                            <th>
                                <input type="text" id="uSecondName" name="secondName"
                                       placeholder="All one word"size="50">
                            </th>

                        </tr>
                        <tr>
                            <th>
                                <label for="uBirthDate">Birth date: </label>
                            </th>
                            <th>
                                <input type="date" id="uBirthDate" name="birthDate"
                                       required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label for="uPhoneNum">Phone number:</label>
                            </th>
                            <th>
                                <input id="uPhoneNum" name="phoneNum" type="tel">
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <input type="hidden" name="newPage" value="addNewUser">
                                <input type="submit" value="Submit"/>
                            </th>
                            <th>
                                <input type="reset" value="Clear">
                            </th>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="c_3 transp">CONTENT3

            </div>
        </div>
    </div>

    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>