<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${msg }</h1>
	<c:forEach var="store" items="${list}">
	 <ul>
	 	<li>${store.sno} ${store.sname} ${store.lat} ${store.lng}</li>
	 </ul>
	</c:forEach>
</body>
</html>