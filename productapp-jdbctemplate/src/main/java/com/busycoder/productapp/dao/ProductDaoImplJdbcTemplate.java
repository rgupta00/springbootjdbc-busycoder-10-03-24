package com.busycoder.productapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Primary
@Repository
public class ProductDaoImplJdbcTemplate implements ProductDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImplJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from product_table", new ProductRowMapper());
    }

    @Override
    public Product addProduct(Product product) {
        //i need to ask jdbcTemplate to return the auto gen key
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String add_query="insert into product_table(name,price) values(?,?)";
      // jdbcTemplate.update(add_query,new Object[]{product.getName(), product.getPrice()});
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt=con.prepareStatement(add_query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                //pstmt.executeUpdate();
                return pstmt;
            }
        },keyHolder);
        int pk=keyHolder.getKey().intValue();
        product.setId(pk);
       return product;
    }

    @Override
    public Product getById(int id) {
        return jdbcTemplate.queryForObject("select * from product_table where id=?", new ProductRowMapper(), id);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        String update_query="update product_table set price=? where id=?";
        jdbcTemplate.update(update_query,new Object[]{product.getPrice(), product.getId()});
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        String delete_query="delete from product_table where id=?";
        jdbcTemplate.update(delete_query,id);
    }
}
