<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@<br>
	<tiles:insertAttribute name="left"/>
	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@<br>
	<tiles:insertAttribute name="content"/>
testLayout.jsp
</body>
</html>