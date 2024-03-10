package com.busycoder.productapp.service;

import com.busycoder.productapp.dao.Product;
import com.busycoder.productapp.dao.ProductDao;
import com.busycoder.productapp.exceptions.ProductNotFoundExcption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //declartive way to handle tx mgt
//and it use aop under the hood
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public Product getById(int id) {
        Product product=productDao.getById(id);

        if(product==null)
            throw  new ProductNotFoundExcption("product with id "+id+" not found");

        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return productDao.updateProduct(id, product);
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }
}
