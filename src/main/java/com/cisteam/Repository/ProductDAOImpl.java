package com.cisteam.Repository;

import com.cisteam.mapper.ProductMapper;
import com.cisteam.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{

    JdbcTemplate template;

    DataSource dataSource;

    public ProductDAOImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getAllProducts() {
      String sql="select * from products";
      List<Product>allproducts=template.query(sql,new ProductMapper());
      return allproducts;
    }

    @Override
    public Product getProductById(int id) {
        String sql="select id , name , quantity , price , date from products where id=?";
        return template.queryForObject(sql,new Object[]{id},new ProductMapper());
    }

    @Override
    public void addProduct(Product product) {
        Object[]productInfo={product.getName(),product.getQuantity(),product.getPrice(),product.getDate()};
        String sql="insert into products (name,quantity,price,date) values (?,?,?,?)";
        template.update(sql,productInfo);
    }

    @Override
    public int deleteProduct(int id) {
        String sql="delete from products where id="+id;
       return template.update(sql);
    }

    @Override
    public int editProduct(Product product) {
         String sql="update products set name='"+product.getName()+"',quantity='"
                 +product.getQuantity()+"',price='"+product.getPrice()+"' where id="+product.getId();
       return  template.update(sql);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
     this.dataSource=dataSource;
     this.template=new JdbcTemplate(dataSource);
    }

}
