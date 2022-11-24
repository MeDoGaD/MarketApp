<%@ include file="includes/header.jspf" %>
<%@ include file="includes/navbar.jspf" %>
<body>


<div class="container">

<form action="/add-product-admin" method="POST">
  <div class="form-group">
    <label for="exampleInputEmail1">name </label>
    <input type="text" class="form-control" name="name" required>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Quantity</label>
    <input type="text" class="form-control"  name="quantity" required>
  </div>
  <div class="form-group">
      <label for="exampleInputPassword1">Price</label>
      <input type="text" class="form-control"  name="price" required>
    </div>
  <button type="submit" class="btn btn-primary">Add Product</button>
</form>
</div>


<br>

<%@ include file="includes/footer.jspf" %>