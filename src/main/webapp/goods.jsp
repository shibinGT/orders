<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="entity.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
  <%@ taglib prefix="w" uri="http://weborders.com/tag/pager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
td{


padding-left: 50px;
padding-right: 50px;
}
th{
padding-left: 50px;
padding-right: 50px;

}
td a{
 	text-decoration: none;
 	color:	blue;	
 }

</style>
</head>
<body style="background-color: #D1EEEE;">

<jsp:include page="menu.jsp">
	<jsp:param value="goods" name="menu"/>
</jsp:include>

<div style="text-align: center;">
	<div style="width:500px;margin: 0px auto;margin-top:20px; text-align: center;">
		<form style="display:inline-block; action="<%=request.getContextPath()%>/goodsservlet" method="post">
			<input  type="text" name="goods_name" value="<%=request.getAttribute("value")%>">
			<input  type="submit" value="查询">
		</form>
		&nbsp;
		<form style="display:inline-block;" action="<%=request.getContextPath()%>/addgoods.jsp">
			<input type="submit" value="添加商品">
		</form>
		<%session.setAttribute("token", new Date().getTime()+""); %>
		
		<form id="fileForm" action="<%=request.getContextPath()%>/fileservlet" enctype="multipart/form-data" method="post">
			<input type="hidden" name="token" value="${sessionScope.token}">
			<input type="file" name="file" id="fileID">			
		</form>
		<button onclick="goodsfileadd()">批量增加</button>
	</div>
	<div style="display: none" id="wait">
	<img style="width:500px;height: 200px;float:left;" src="<%=request.getContextPath()%>/img/timg.gif">
	</div>
	
	<table >
		<tr>
			<th>
				序号
			</th>
			<th>
				商品
			</th>
			<th>	
				价格
			</th>
			<th>	
				库存
			</th>
			<th>	
				图片
			</th>
			<th>	
				描述
			</th>
		</tr>
		 <!--商品表 --> 
			<c:forEach items="${goodslist}" var="list" varStatus="s">
		<tr>
			<td>
				<c:out value="${s.index+1}"></c:out>
			</td>
			<td>
				<c:out value="${list.getGoods_name()}"></c:out>
			</td>
			<td>
				<c:out value="${list.getPrice()}"></c:out>
			</td>
			<td>
				<c:out value="${list.getStock()}"></c:out>
			</td>
			<td>
				<img style="width:200px;height: 100px;" src="<%=request.getContextPath()%>/img/${list.getPicture()} "/>
			</td>
			<td>
				<c:out value="${list.getDescribes()}"></c:out>
			</td>
		</tr>
		<tr >
			<td style="padding-bottom: 20px;">
				<a href="javascript:void()" onclick="dele(${list.getGoods_id()})">删除</a>
			</td>
			<td style=" padding-bottom: 20px; ">
				<a href="<%=request.getContextPath()%>/goodschange.jsp?goods_id=${list.getGoods_id()}">修改</a>
			
			</td>
		</tr>
			</c:forEach>
	</table>
	
</div>
<div>
	 <w:pager pageSize="${page.pageSize}" pageNo="${page.pageNo }" url="goodsservlet" recordCount="${page.recordCount }"/>
	
	
	
</div>
<script type="text/javascript">
	
	function dele(goods_id) {
		if(confirm("你确定要删除吗？")){
			window.location.href="<%=request.getContextPath()%>/goodsdelete?goods_id="+goods_id;
		}
		
	}	
	function goodsfileadd(){
		var fileName=document.getElementById("fileID").value;
		var reg=/\.xls$/;
		if(reg.test(fileName)){
			document.getElementById("wait").style.display="block"
			document.getElementById("fileForm").submit();
		}else{
			alert("请选择上传文件，必须是Excel文件");
		}
	}
</script>
</body>
</html>