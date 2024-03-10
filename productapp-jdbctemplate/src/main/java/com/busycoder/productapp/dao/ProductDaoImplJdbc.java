package com.busycoder.productapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImplJdbc implements ProductDao{

    private DataSource dataSource;

    @Autowired
    public ProductDaoImplJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAllProducts() {
        //DM.getConnnection vs DataSource.getConnection
        List<Product> products=new ArrayList<>();

        Connection connection=null;
        try{
             connection=dataSource.getConnection();

            PreparedStatement pstmt=connection.prepareStatement("select * from product_table");
            ResultSet rs=pstmt.executeQuery();

            while (rs.next()){
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product addProduct(Product product) {
        Connection connection=null;
        try{
            connection=dataSource.getConnection();

            PreparedStatement pstmt=connection.prepareStatement("insert into product_table(name,price) values(?,?)");
            int noOfRowsEffected=pstmt.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
