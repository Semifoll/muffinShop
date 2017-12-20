<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class = "c_1 transp">
    <h2>Список доступных страниц:</h2><br>
    <a href="${pageContext.request.contextPath}/productList"><b>Список товаров</b></a><br>
    <a href="${pageContext.request.contextPath}/login"><b>Личный кабинет</b></a><br>
    <a href="${pageContext.request.contextPath}/orderList"><b>Корзина</b></a><br>
    <a href="${pageContext.request.contextPath}/registration"><b>Registration</b></a><br>
    <a href="${pageContext.request.contextPath}/info"><b>Info</b></a><br>


</div>