package com.cisteam.mapper;

import com.cisteam.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
       Product p=new Product();
       p.setId(resultSet.getInt("id"));
       p.setName(resultSet.getString("name"));
       p.setQuantity(resultSet.getInt("quantity"));
       p.setPrice(resultSet.getDouble("price"));
       p.setDate(resultSet.getDate("date"));
       return p;
    }
}
