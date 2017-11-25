<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
	.mytr2 th{
		background-color: 	#D1EEEE;
		width: 200px;
		text-align: center;
		margin: 0px auto; 
	}
	.mytr2 td{
	background-color: 	#D1EEEE;
		margin: 0px auto; 
		width: 200px;
		text-align: center;
	}
</style>
</head>
<body style="background-color: #D1EEEE;">
	<jsp:include page="menu.jsp">
		<jsp:param value="myorders" name="menu" />
	</jsp:include>
	<div  id="ordercheck">
		
		<h3>我的订单：</h3>
			<table >
				<tr class="mytr">
					<th>
					订单：
					</th>
					<th>
					订单总额：
					</th>
					<th>
					订单创建时间：
					</th>
					<th>
					订单结算时间：
					</th>
					<th>
					查看商品：
					</th>
					<th>
					订单操作：
					</th>
				</tr>
				<c:forEach items="${myorderslist}" var="list" varStatus="li">
				<tr class="mytr">
				<td>
				${li.index+1}
				</td>
				<td>
				${list.getMoney()}
				</td>
				<td>
				${list.getCreatetime()}
				</td>
				<td>
				${list.getPaymenttime()}
				</td>
				<td>
						<!-- 状态为结算则显示 -->
					<input type="button" name="check" id="checkid" value="查看商品" onclick="checkgoods(${list.getOrders_id()})">
				<!-- 订单状态 c:if标签-->
				</td>
				<td>
				<c:if test="${list.getStatus()==1 }">
				<button onclick="dele(${list.getOrders_id()})">删除订单</button>
				<input type="button" onclick="pay(${list.getOrders_id()})" value="支付">
				
				</c:if>
				<c:if test="${list.getStatus()==2 } "></c:if>
				</td>
			
			</tr>
			</c:forEach>
				</table>
	</div>
	
	<!-- 商品 -->
	<div id="ordergoods" >
		<c:if test="${orders_goodslist==null}">
		</c:if>
		
		<c:if test="${orders_goodslist!=null}">
			<table>
				<tr  class="mytr2">
					<th>商品</th>
					<th>单价</th>
					<th>数量</th>
				</tr>
			<c:forEach items="${orders_goodslist}" var="goodslist" varStatus="gl">
				<tr  class="mytr2">	
					<td>
					
		${goodslist.getGoods_name()}
					</td>
					<td>
					
			${goodslist.getPrice()}
					</td>
					<td>
			${goodslist.getNumber()}
					
					</td>
				</tr>
			</c:forEach>
				
			</table>
			
		</c:if>
	</div>
</body>
<script type="text/javascript">
	
	function checkgoods(orders_id) {
		var ordergoods=document.getElementById("ordergoods");
		
		window.location.href="<%=request.getContextPath()%>/orderscheck?orders_id="+orders_id;
	}
	function dele(deleorders_id){
		if(confirm("你确定要删除吗？")){
		window.location.href="<%=request.getContextPath()%>/ordersdelete?deleorders_id="+deleorders_id;
		}
	}
	function pay(orders_id){
		
		
		window.location.href="<%=request.getContextPath()%>/orderspaymoney?orders_id="+orders_id;
		}
	
</script>
</html>