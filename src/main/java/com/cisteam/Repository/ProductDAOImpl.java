package com.cisteam.Repository;

import com.cisteam.mapper.ProductMapper;
import com.cisteam.models.Cart;
import com.cisteam.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
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


    public List<String> checkValidOrder(){
        List<String>errors=new ArrayList<>();
        List<Product>allProducts=getAllProducts();
        Cart c=Cart.getInstance();
        for (int i=0;i< c.getProducts().size();i++){
            Product p=getProductById(c.getProducts().get(i).getId());
            if(p.getQuantity()>=c.getProducts().get(i).getQuantity())
                continue;
            else {
                errors.add(String.format("The available quantity of %s you can buy is %d",p.getName(),p.getQuantity()));
            }
        }
        return errors;
    }

    public boolean UpdateStoreItems_AfterOrder(){
        Product p=new Product();
        List<Product>allProducts=getAllProducts();
        Cart c=Cart.getInstance();
        try {
            for (int i = 0; i < c.getProducts().size(); i++) {
                p = getProductById(c.getProducts().get(i).getId());
                int newQuan = p.getQuantity() - c.getProducts().get(i).getQuantity();
                p.setQuantity(newQuan);
                editProduct(p);
            }
        }catch (Exception ex){
            return false;
        }
        finally {
            return true;
        }
    }

}
