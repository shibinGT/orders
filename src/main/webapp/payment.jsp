<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: #D1EEEE;">

	<div>
		<div>
		订单：
				订单总额：<span id="money">${orders.money}</span><br />
				订单创建时间：${orders.createtime}<br />
		
		</div>
		<div>
		商品详情：
		<c:forEach items="${orders_goodslist}" var="goodslist" varStatus="gl">
			商品：${goodslist.getGoods_name()}
			单价：${goodslist.getPrice()}
			数量：${goodslist.getNumber()}
			
			</c:forEach>
		</div>
	</div>
	
	
	请输入支付金额：<input type="text" name="paymoney" id="paymoneyid">
	<button onclick="pay(${orders.orders_id})"> 支付</button>
	<div>
	<a href="orderscheck">稍后支付</a>
	</div>
</body>
<script type="text/javascript">
	function pay(orders_id) {
		var money=document.getElementById("money").innerText;
		var paymoney=document.getElementById("paymoneyid").value;
		//要转成int类型再比较
		if(parseFloat(paymoney)<parseFloat(money)){
			alert("金额不足，请重新支付");
		}
		else if(parseFloat(paymoney)==parseFloat(money)){
			alert("支付成功，货物马上送到！");
			window.location.href="<%=request.getContextPath()%>/paymoney?orders_id="+orders_id;
		}else if(parseFloat(paymoney)>parseFloat(money)){
			alert("支付成功，货物马上送到！找零："+(paymoney-money))
			window.location.href="<%=request.getContextPath()%>/paymoney?orders_id="+orders_id;
		}
	}
</script>
</html>