<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/goodsAdd" enctype="multipart/form-data" method="post">
		商品名：<input type="text" name="goods_name"><br/>
		价格：<input type="text" name="price"><br/>
		库存：<input type="text" name="stock"><br/>
		图片：<input type="file" name="picture"><br/>
		描述：<input type="text" name="describes"><br/>
		<input type="submit">
	</form>
</body>
</html>