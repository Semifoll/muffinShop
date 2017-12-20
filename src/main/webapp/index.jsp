<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Muffin Shop</title>
    <link href = "${pageContext.request.contextPath}/resources/CSS/page_style.css" rel = "stylesheet" type = "text/css"/>

</head>

<body>
<div class = "wrapper">

		<jsp:include page="WEB-INF/views/_header.jsp"></jsp:include>


		<jsp:include page="WEB-INF/views/_menuline.jsp"></jsp:include>
	<div class = "midle_body">
		<!-- 2.1 -->
		<div class = "content row">
			<div class = "c_1 transp">CONTENT1</div>

			<div class = "c_2 transp">CONTENT2</div>

			<div class = "c_3 transp">CONTENT3

			</div>
		</div>
	</div>

		<jsp:include page="WEB-INF/views/_footer.jsp"></jsp:include>

</div>

</body>
</html>