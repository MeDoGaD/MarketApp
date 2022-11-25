<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/user_navbar.jspf" %>
<body>


<div class="jumbotron text-center">
  <h1>Your Cart</h1>
</div>
<div class="container">
 <table class="table table-condensed">
    <thead>
      <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price/Unit</th>
        <th>Item total price</th>
        <th>Action</th>
      </tr>
    </thead>

    <tbody>
    <c:forEach items="${cart_products}" var="product">
    <tr>
        <td>${product.name}</td>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
        <td>${product.price*product.quantity}</td>
        <td>
        <a href="/delete-product-cart?id=${product.id}" class="btn btn-danger">Delete</a>
        </td>
    </tr>
  <br>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="jumbotron text-center" style="height:10px">
 <a href="/user-product-confirm" class="btn btn-success">Confirm order <br> ${total}$</a>
</div>

<%@ include file="includes/footer.jspf" %>