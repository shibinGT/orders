
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="entity.Goods"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="w" uri="http://weborders.com/tag/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#left {
	float: left;
	margin: 0px;
	width: 70%;
}

a {
	text-decoration: none;
	color: green;
}

#right {
	float: left;
	margin-left: 80px;
	width: 20%;
}

.goodsdiv {
	width: 25%;
	float: left;
	padding: 10px;
	margin: 10px;
	float: left;
}

#max {
	width: 100%;
}
</style>
</head>
<body style="background-color: #D1EEEE;">
	<jsp:include page="menu.jsp">
		<jsp:param value="shopping" name="menu" />
	</jsp:include>
	<div id="max">
		<div id="left">
			<!-- 商品列表，添加到购物车功能 -->
			<div>
				<div>
					<span style="color: green; padding-left: 20px;">买点什么：</span>

					<form action="<%=request.getContextPath()%>/shoppingservlet"
						method="post">
						<input type="text" name="goods_name"
							value="<%=request.getAttribute("value")%>"> <input
							type="submit" value="查询商品">
					</form>
				</div>
				<c:forEach items="${goodslist}" var="list" varStatus="s">

					<div class="goodsdiv">
						<div class="picture">
							<img style="width: 100px; height: 50px;"
								src="<%=request.getContextPath()%>/img/${list.getPicture()} ">
						</div>
						<div class="foot">
							商品：${list.getGoods_name()} <br /> 价格：${list.getPrice()} <br />
							描述：${list.getDescribes()} <br /> 库存：${list.getStock()}<br />
						</div>
						<a
							href="<%=request.getContextPath()%>/shoppingCart?goods_id=${list.getGoods_id()}">添加到购物车</a>
					</div>
				</c:forEach>
			</div>
			<br />

			<div style="float: left;">
				<w:pager pageSize="${page.pageSize}" pageNo="${page.pageNo }"
					url="shoppingservlet" recordCount="${page.recordCount }" />
			</div>
		</div>
		<!-- ---------------------------------------- -->

		<div id="right">
			<!-- 购物车，添加订单功能 -->
			<h2>购物车</h2>
			<c:forEach items="${cartlist}" var="list" varStatus="li">
				<div style="float: left; width: 50%;">
					<h6>商品名称：</h6>${list.getGoods_name()}<br />
					<h6>数量：</h6>${list.getNumber()}
					<br /> <a
						href="<%=request.getContextPath()%>/deleteCart?deletegoods_id=${list.getGoods_id()}">移除商品</a>
				</div>
			</c:forEach>
			<a href="addorders"><h2>去支付</h2></a>
		</div>
	</div>
</body>
</html>