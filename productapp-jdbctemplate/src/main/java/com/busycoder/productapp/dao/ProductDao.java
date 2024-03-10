package com.busycoder.productapp.dao;

import java.util.List;

public interface ProductDao {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product getById(int id);
    public Product updateProduct(int id, Product product);
    public void deleteProduct(int id);
}
