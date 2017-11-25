<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
     <%@ taglib prefix="w" uri="http://weborders.com/tag/pager" %>
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
	<jsp:param value="allorders" name="menu"/>
</jsp:include>
	<div>
		<div style="margin-left: 210px ;background-color:#D1EEEE ;width: 300px;">
	想看哪个人的订单？(无输入查看全部)：
	<form action="allorderscheck" method="post">
	<input type="text" name="user_name" value="${value}">
	<input type="submit" value="查看">
		
		<select name="status">
		    <option value="all" 
		    <c:if test="${value2=='all'}"> selected="selected" 
		    </c:if>
		    >全部</option>
		    <option value="yes"
		     <c:if test="${value2=='yes'}"> selected="selected" </c:if>
		    >已付款</option>
		    <option value="no"
		    <c:if test="${value2=='no'}"> selected="selected" </c:if>
		    >未付款</option>
		    
	  	</select>
		
	</form>
	</div>
		<table >
			<tr class="mytr">
				<th>
				订单号
				</th>
				<th>
				订单用户
				</th>
				<th>
				订单总额
				</th>
				<th>
				订单创建时间
				</th>
				<th>
				订单结算时间
				</th>
				<th>
				订单商品
				</th>
			</tr>
		<c:forEach items="${orderslist}" var="list" varStatus="">
			<tr class="mytr">
				<td>
		${list.getOrders_id()}
				</td>
				<td>
		${list.getUser_name()}
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
					<input type="button" name="check" id="checkid" value="查看商品" onclick="checkgoods(${list.getOrders_id()})">
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
					<th>订单号</th>
					<th>单价</th>
					<th>数量</th>
				</tr>
			<c:forEach items="${orders_goodslist}" var="goodslist" varStatus="gl">
				<tr  class="mytr2">	
					<td>
					
		${goodslist.getGoods_name()}
					</td>
								<td>
					
		${goodslist.getOrders_id()}
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
	<div>
	 <w:pager pageSize="${page.pageSize}" pageNo="${page.pageNo }" url="allorderscheck" recordCount="${page.recordCount }"/>
	</div>
</body>
<script type="text/javascript">
	function checkgoods(orders_id) {
		var ordergoods=document.getElementById("ordergoods");
		
		window.location.href="<%=request.getContextPath()%>/allorderscheck?orders_id="+orders_id;
	}
</script>
</html>