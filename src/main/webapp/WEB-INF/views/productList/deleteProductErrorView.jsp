<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ page isELIgnored="false" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
   <head>
      <meta charset="UTF-8">
      <title>Delete product</title>
	  <link href = "${pageContext.request.contextPath}/resources/CSS/page_style.css" rel = "stylesheet" type = "text/css"/>
	
   </head>
 
 <body>

 <jsp:include page="../_header.jsp"></jsp:include>


 <jsp:include page="../_menuline.jsp"></jsp:include>
    
    <h3>Delete Product</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="">Product List</a>
    
    <jsp:include page="../_footer.jsp"></jsp:include>
    
 </body>
</html>