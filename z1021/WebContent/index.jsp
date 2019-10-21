<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@page import="org.zerock.util.*" %>

<%
	Connection con = CPUtil.INSTANCE.getDS().getConnection();
	PreparedStatement pstmt = con.prepareStatement("select sysdate from dual");
	ResultSet rs = pstmt.executeQuery();
	
	rs.next();
	
	String str = rs.getString(1);
	
	rs.close();
	pstmt.close();
	con.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=str %></h1>
</body>
</html>