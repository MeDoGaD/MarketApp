<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/user_navbar.jspf" %>
<body>


<div class="jumbotron text-center">
  <h1>Select your products</h1>
</div>
<div class="container">
 <table class="table table-condensed">
    <thead>
      <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
           <a href="/cart" class="btn btn-success">Show Your Cart</a>
      </tr>
    </thead>

    <tbody>
    <c:forEach items="${products}" var="product">
    <tr>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>
        <form action="/add-product-user">
        <div class="form-group">
            <input type="text"   name="id" value="${product.id}" hidden  required>
            <input type="number" class="form-control" name="quantity" style="width: 7em" required>
          </div>
               <button type="submit" class="btn btn-primary">Add to cart</button>
        </form>
        </td>
    </tr>
  <br>
    </c:forEach>
    </tbody>

  </table>
</div>

<%@ include file="includes/footer.jspf" %>