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

<img src="fileView?fname="${fname}>

<form action="upload" method="post" enctype="multipart/form-data">
	<input type="file" name="fs" multiple="multiple"></input>
	<button>Upload</button>
</form>

</body>
</html>