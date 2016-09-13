<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/orders" method="post">
  
	<c:if test="${not empty order.id}">
		<input type="hidden" name="order.id" value="${order.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Number:<br />
	
		<input type="text" name="order.number" value="${order.number}"/>
	</div>
	
	<div class="field">
		Client:<br />
	
		<input type="text" name="order.client" value="${order.client}"/>
	</div>
	
	<div class="field">
		Total value:<br />
	
		<input type="text" name="order.totalValue" value="${order.totalValue}"/>
	</div>
	
	<div class="field">
		Products:<br />
	
		<input type="text" name="order.products" value="${order.products}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/orders">Back</a>
