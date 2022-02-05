<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.sendOrder").click(function () {
				return confirm("您确定要发货吗？")
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.jpg" >
			<span class="wel_word">订单管理系统</span>
			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>		

			<c:forEach items="${requestScope.allOrders}" var="allOrders">
				<tr>
					<td>${allOrders.createTime}</td>
					<td>${allOrders.price}</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${allOrders.orderId}">查看详情</a></td>
					<td>
						<c:if test="${allOrders.status==0}">
							<a class="sendOrder" href="orderServlet?action=sendOrder&orderId=${allOrders.orderId}">点击发货</a>
						</c:if>
						<c:if test="${allOrders.status==1}">
							等待收货
						</c:if>
						<c:if test="${allOrders.status==2}">
							已签收
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>