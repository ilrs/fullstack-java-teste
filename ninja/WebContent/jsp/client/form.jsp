<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/clients/" method="post">
  
	<c:if test="${not empty client.id}">
		<input type="hidden" name="client.id" value="${client.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Cpf cnpj:<br />
	
		<input type="text" name="client.cpfCnpj" value="${client.cpfCnpj}"/>
	</div>
	
	<div class="field">
		Social name:<br />
	
		<input type="text" name="client.socialName" value="${client.socialName}"/>
	</div>
	
	<div class="field">
		Phone:<br />
	
		<input type="text" name="client.phone" value="${client.phone}"/>
	</div>
	
	<div class="field">
		Email:<br />
	
		<input type="text" name="client.email" value="${client.email}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/clients">Back</a>
