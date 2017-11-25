<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.mytr th{
		background-color: #D1EEEE;
		width: 200px;
		text-align: center;
		margin: 0px auto; 
	}
	.mytr td{
	background-color: #D1EEEE;
		margin: 0px auto; 
		width: 200px;
		text-align: center;
	}
</style>
</head>
<body style="background-color: #D1EEEE;">
	
<jsp:include page="menu.jsp">
	<jsp:param value="usercontrol" name="menu"/>
</jsp:include>
<div>
	
	<table>
		<tr class="mytr">
			<th>
				用户id
			</th>
			<th>
				用户名
			</th>
			<th>
				年龄
			</th>
			<th>
				电话
			</th>
			<th>
				权限
			</th>
		</tr>
		
			<c:forEach items="${ userlist}" var="list" varStatus="s">
		<tr class="mytr">
			<td>
					${list.getUser_id() }
				</td>
				<td>
					${list.getUser_name() }
				</td>
				<td>
					${list.getAge() }
				</td>
				<td>
					${list.getPhone() }
				</td>
				<td>
				<c:if test="${list.getType()==1 }">
					用户
				</c:if>
				<c:if test="${list.getType()==2 }">
					管理员
				</c:if>
				</td>
		</tr>
			</c:forEach>
	
	</table>
</div>
</body>
</html>