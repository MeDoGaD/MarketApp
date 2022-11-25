
<%@ include file="includes/header.jspf" %>
<body>
<c:if test="${totalPrice==0}">
    <div class="jumbotron text-center">
      <h1>Select Items first then make an order :) </h1>
    </div>
</c:if>

<c:if test="${errors.size() == 0} && ${totalPrice>0}">
    <div class="jumbotron text-center">
      <h1>Your Order is Done :) </h1>
      <p>With Budget ${totalPrice} $ </p>
      <br>
      <br>
      <p>Nice to see you here :) </p>
    </div>
</c:if>

<c:if test="${errors.size() > 0}">
    <div class="jumbotron text-center">
      <h1>Sorry :( </h1>
      <br>
    <c:forEach items="${errors}" var="error">
          <p>${error}</p>
    </c:forEach>
  </div>
</c:if>


<%@ include file="includes/footer.jspf" %>