<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: #D1EEEE;">
<jsp:include page="menu.jsp">
	<jsp:param value="main" name="menu"/>
</jsp:include>
<div style="text-align: center;margin-top: 50px;">
<img style="width:1200px;height: 500px;" src="<%=request.getContextPath()%>/img/index.jpg"/>
</div>
</body>
</html>