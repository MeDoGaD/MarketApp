<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/navbar.jspf" %>
<body>


<div class="jumbotron text-center">
  <h1>Products Page</h1>
</div>
<div class="container">
 <table class="table table-condensed">
    <thead>
      <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Added Date</th>
        <th>Action</th>
      </tr>
    </thead>

    <tbody>
    <c:forEach items="${products}" var="product">
    <tr>
        <td>${product.name}</td>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
        <td>${product.date}</td>
        <td>
        <a href="/delete-product?id=${product.id}" class="btn btn-danger">Delete</a>
        <a href="/edit-product?id=${product.id}" class="btn btn-success">Edit</a>
        </td>


    </tr>
  <br>
    </c:forEach>
    </tbody>

  </table>
</div>

<%@ include file="includes/footer.jspf" %>