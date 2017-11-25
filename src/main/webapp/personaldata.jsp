<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a {
	border: 2px solid #CFCFCF;
	width: 200px;
}


</style>
</head>
<body style="background-color: #D1EEEE;">
	<jsp:include page="menu.jsp">
		<jsp:param value="personaldata" name="menu" />
	</jsp:include>
	<div
		style="margin-left: 100px; margin-top: 100px; width: 200px; height: 200px;">
		<h3 style="margin-left: 60px;">个人信息</h3>

		<table style="margin-left: 60px;">
			<tr>
				<td class="a"><span>用户名</span></td>
				<td class="a"><span>年龄</span></td>
			</tr>
			<tr>
				<td class="a"><span>${loginuser.user_name}</span></td>
				<td class="a"><span>${loginuser.age }</span></td>
			</tr>
		</table>
		<input style="margin-left: 60px;" type="button" id="" value="修改资料"
			onclick="change()">
	</div>


	<div id="changepassword" style="display: none; margin-left: 170px;">
		<h3>修改密码</h3>
		<form action="personagechange">

			<span style="margin-right:  100px;">新密码:</span> <input type="password" name="password"><br/>
			 <span style="margin-right: 20px;">请再次输入新密码:</span>
			<input type="password" name="password2"><br/>
			 <span style="margin-right: 72px;">验证电话：</span>
			  <input type="text" name="phone"><br/>
			  <input type="submit" value="确认修改">
		</form>
		<div>${msg}</div>
	</div>
</body>
<script type="text/javascript">
	var a = document.getElementById("changepassword");
	function change() {
		a.style.display = "block";
	}
</script>
</html>