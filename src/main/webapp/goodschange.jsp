<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	span{
		color:red;
	}
</style>
</head>
<body>
	
	
	<form action="<%=request.getContextPath()%>/goodsChangeservlet" enctype="multipart/form-data" method="post">
	
		 <input style="display:none" type="text" name="goods_id" value="<%=Integer.parseInt(request.getParameter("goods_id"))%>">
		<span >商品名</span>：<input type="text" name="goods_name"><br/>
		<span>价格：</span><input type="text" name="price"><br/>
		<span>库存：</span><input type="text" name="stock"><br/>
		<span>图片：</span><input type="file" name="picture"><br/>
		<span>描述：</span><input type="text" name="describe"><br/>
			<input type="submit" value="提交修改">
	</form>
</body>
</html>