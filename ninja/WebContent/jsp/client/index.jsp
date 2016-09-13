<head>
	<title>Client [index]</title>
</head>
<body>
	<h1>Listing Clients</h1>

	<table>
		<tr>
			<th>Cpf cnpj</th>
			<th>Social name</th>
			<th>Phone</th>
			<th>Email</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${clientList}" var="client">
			<tr>
				<td>${client.cpfCnpj}</td>
				<td>${client.socialName}</td>
				<td>${client.phone}</td>
				<td>${client.email}</td>
				<td><a href="${pageContext.request.contextPath}/clients/${client.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/clients/${client.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/clients/new">New Client</a> 
</body>