<%@ include file="includes/header.jspf" %>
<%@ include file="includes/navbar.jspf" %>
<body>


<div class="container">

<form action="/edit-product" method="POST">
    <input type="text"   name="id" value="${product.id}" hidden  required>

  <div class="form-group">
    <label for="exampleInputEmail1">name </label>
    <input type="text" class="form-control" name="name" value="${product.name}" required>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Quantity</label>
    <input type="text" class="form-control"  name="quantity" value="${product.quantity}" required>
  </div>
  <div class="form-group">
      <label for="exampleInputPassword1">Price</label>
      <input type="text" class="form-control"  name="price" value="${product.price}" required>
    </div>
  <button type="submit" class="btn btn-primary">Edit Product</button>
</form>
</div>


<br>

<%@ include file="includes/footer.jspf" %>