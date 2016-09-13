<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/products" method="post">
  
	<c:if test="${not empty product.id}">
		<input type="hidden" name="product.id" value="${product.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Code:<br />
	
		<input type="text" name="product.code" value="${product.code}"/>
	</div>
	
	<div class="field">
		Description:<br />
	
		<input type="text" name="product.description" value="${product.description}"/>
	</div>
	
	<div class="field">
		Quantity:<br />
	
		<input type="text" name="product.quantity" value="${product.quantity}"/>
	</div>
	
	<div class="field">
		Unit value:<br />
	
		<input type="text" name="product.unitValue" value="${product.unitValue}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/products">Back</a>
