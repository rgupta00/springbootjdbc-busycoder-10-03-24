package com.busycoder.productapp.controller;

import com.busycoder.productapp.dao.Product;
import com.busycoder.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //get all the records
    @GetMapping(path = "products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //get an product by id
    @GetMapping(path = "products/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getById(id);
    }

    @PutMapping(path = "products/{id}")
    public Product updateProductById(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id, product);
    }


    //@RequestBody: it trigger the parser to convert json to java object
    @PostMapping(path = "products")
    public Product addProduct(@RequestBody  Product product){
        return productService.addProduct(product);
    }



}
