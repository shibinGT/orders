<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
<%-- 
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jd.js"></script>
--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
</head>
<body>
<div style="width:800px;height: 500px;border: 1px solid red;margin: 0px auto;">
		<div style="width:100%;height: 100px;background-color: red;">
			<h1>注册</h1>
		</div>
		<div>
			<form action="registerServlet" method="post" id="form">
			<table style="">
			<tr>
				<td align="right"><span>用户名：</span> </td>
				<td>
				<input type="text" name="user_name" id="user" onblur="check()">
				<span class="span1" style="background-color:#E0EEEE ;"></span> 
				<span id="span"></span>
				</td>
			</tr>
			<tr>
				<td align="right"><span>密码：</span> </td>
				<td>
				<input type="password" name="password" id="pass1">
				<span class="span2" style="background-color:#E0EEEE ;"></span>
				</td>
			</tr>
			<tr>
				<td align="right"><span>再次输入密码：</span> </td>
				<td><input type="password" name="password2" id="pass2">
				<span class="span3" style="background-color:#E0EEEE ;"></span>
				</td>
			</tr>
			<tr>
				<td align="right"><span>年龄：</span> </td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td align="right"><span>电话：</span> </td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				
					<input type="button" value="注册" onclick="put()" >
					
					<a href="index.jsp">去登陆</a>
				</td>
			</tr>
			<tr>
			<td>
			${msg }
			</td>
			</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
function check(){
	var username=document.getElementById("user").value;
	var url= "ajax?username="+username;
	$.get(url,function(data){
		document.getElementById("span").innerText=data;
	})
}
function put(){
	var password1=document.getElementById("pass1").value;
	var password2=document.getElementById("pass2").value;
	 var hash1=hex_md5(password1);
	var hash2=hex_md5(password2);
	
	document.getElementById("pass1").value=hash1;
	document.getElementById("pass2").value=hash2;
	document.getElementById("form").submit();
}
</script>
</html>