<%@page import="entity.User"%>
<%@page import="listenter.Listenter2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
	margin: 0px auto;
	}
 .max{
 	
 	width:100px;
 	height:50px;
 	font-size:18px;
 	text-align: right;
	display:inline-block;
 	margin-bottom: 0px;
 	margin-top: 28px;
 	margin-left: 0px;
	margin-right:10px;
 	list-style: none;
 
 }
.max a{
 	text-decoration: none;
 	color:white	;
 	
 }
 .max a:HOVER{
 	background-color: #D1EEEE;
 }
 
 
 .topfont{
 font-size:16px;
 color:white;
 }
</style>
</head>
<%String menu=request.getParameter("menu"); %>
<body>
	<div style="width: 1440px;">
	<div style="text-align: center;background-color: #668B8B;height: 50px;width:100%;" >
		<ul>
			<li class="max">
				<a href="<%=request.getContextPath()%>/main.jsp"
				<%if("main".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>
				>首页</a>
			</li >
			
			<li class="max">
			<a href="<%=request.getContextPath()%>/shoppingservlet"
			<%if("shopping".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>>购物中心</a>
			</li>
			
			<c:if test="${loginuser.type==2 }">
			<li class="max">
				<a href="<%=request.getContextPath()%>/goodsservlet"
				<%if("goods".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>
				>商品管理</a>
			</li>
			<li class="max">
				<a href="<%=request.getContextPath()%>/allusercheck"
				<%if("usercontrol".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>>用户管理</a>
			</li>
			<li class="max">
				<a href="<%=request.getContextPath()%>/allorderscheck"
				<%if("allorders".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>
				>订单中心</a>
			</li>
			
			</c:if>
			
			<li class="max">
				<a href="<%=request.getContextPath()%>/personaldata.jsp"
				<%if("personaldata".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>
				>个人中心</a>
			</li>
			<li class="max">
				<a href="<%=request.getContextPath()%>/orderscheck"
				<%if("myorders".equals(menu)){ %>
				style="background-color: #D1EEEE;"<%} %>>个人订单</a>
			</li>
			
			<li class="max" style="margin-left: 20px;">
				<span class="topfont">欢迎你：${loginuser.user_name}</span>
			</li>
		
			
			<li class="max">
				<span class="topfont">登陆人数:<%=User.getUsernumber()%></span>
			</li>
			<li class="max">
				<span class="topfont">在线人数：<%=Listenter2.getNumber() %></span>
			</li>
			<li class="max">
				<a href="logout">退出登陆</a>
			</li>
		</ul>
		</div>
	</div>
	
</body>
</html>