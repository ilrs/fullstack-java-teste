<head>
	<title>Order [index]</title>
</head>
<body>
	<h1>Listing Orders</h1>

	<table>
		<tr>
			<th>Number</th>
			<th>Emission date</th>
			<th>Client</th>
			<th>Total value</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${orderList}" var="order">
			<tr>
				<td>${order.number}</td>
				<td>${order.emissionDate}</td>
				<td>${order.client}</td>
				<td>${order.totalValue}</td>
				<td><a href="${pageContext.request.contextPath}/orders/${order.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/orders/${order.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/orders/new">New Order</a> 
</body>