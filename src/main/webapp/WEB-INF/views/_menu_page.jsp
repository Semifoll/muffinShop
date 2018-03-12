<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    button[type=submit] {
        border-radius: 5px;
        border: 0;
        margin-left: 5px;
        margin-top: 3px;
        min-width: 100px;

        height:25px;
        font-family: Tahoma;
        background: #f4f4f4;
        /* Old browsers */
        background: -moz-linear-gradient(top, #f4f4f4 1%, #ededed 100%);
        /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(1%, #f4f4f4), color-stop(100%, #ededed));
        /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top, #f4f4f4 1%, #ededed 100%);
        /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top, #f4f4f4 1%, #ededed 100%);
        /* Opera 11.10+ */
        background: -ms-linear-gradient(top, #f4f4f4 1%, #ededed 100%);
        /* IE10+ */
        background: linear-gradient(to bottom, #f4f4f4 1%, #ededed 100%);
        /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f4f4f4', endColorstr='#ededed', GradientType=0);
        /* IE6-9 */
    }
</style>
<div class="c_1 transp">
    <h2>Список доступных страниц:</h2><br>
    <form action="${pageContext.request.contextPath}/pack" method="post">

        <button type="submit" name="newPage" value = "ourProductPage">Our products</button><br/>
        <button type="submit" name="newPage" value = "loginPage">Personal cabinet</button><br/>
        <button type="submit" name="newPage" value = "clientOrdersPage">Your orders</button><br/>
        <button type="submit" name="newPage" value = "registrationPage">Registration</button><br/>
        <button type="submit" name="newPage" value = "infoPage">Info</button><br/>

    </form>
</div>