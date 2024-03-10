package com.busycoder.productapp.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        //help spring jdbc to uderstand how to convert rs to object
        System.out.println("it is called "+rowNum);
       return  new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3));
    }
}
