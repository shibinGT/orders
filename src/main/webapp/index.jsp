<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
*{margin: auto}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
</head>
<%
Cookie[] cookie=request.getCookies();
String user_name="";
if(cookie!=null){
	for(Cookie coo:cookie){
		if(coo.getName().equals("user_name")){
			user_name=coo.getValue();
		}
	}
}
%>
<body>
	<div style="width:500px;height: 500px;background-color: ;border: 1px solid #00EEEE;">
		<div id="a1" style="width:500px;height: 100px;">
			 <img style="width:500px;height: 100px;" src="<%=request.getContextPath()%>/img/timg.jpg"/>
		</div>
	
		<div>
			<form action="logins" method="post"  id="form">
			<table>
			<tr>
				<td align="right"><span>用户名：</span> </td>
				<td><input type="text" name="user_name" value="<%=user_name %>"> </td>
			</tr>
			<tr>
				<td align="right"><span>密码：</span> </td>
				<td><input type="password" name="password" id="pass"></td>
			</tr>
			<tr>
				<td  colspan="2" align="center"  >
					<input  type="checkbox" name="rememberme" value="1" <%if(!user_name.equals("")){ %>checked="checked"<%} %>>记住用户名
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="登陆" onclick="put()">
					
					<a href="<%=request.getContextPath() %>/register.jsp">去注册</a>
				</td>
			</tr>
			<tr>
			<td>
			<%if(!(request.getAttribute("msg")==null))
			{ %>
				 <%= request.getAttribute("msg")%>
				<%} %>
			</td>
			</tr>
				</table>
			</form>
					
		</div>
		
	</div>
</body>
<script type="text/javascript">
function put(){
	var password=document.getElementById("pass").value;
	var hash=hex_md5(password);
	document.getElementById("pass").value=hash;
	document.getElementById("form").submit();
}
</script>
</html>