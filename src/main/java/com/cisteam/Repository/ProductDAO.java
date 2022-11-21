package com.cisteam.Repository;

import com.cisteam.models.Product;

import javax.sql.DataSource;
import java.util.List;

public interface ProductDAO {
  List<Product> getAllProducts();
  Product getProductById(int id);
  void addProduct(Product product);
  int deleteProduct(int id);

  int editProduct(Product product);
public void setDataSource(DataSource dataSource);
}
