package com.ra.md03_ss19.DAO;

import com.ra.md03_ss19.entity.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> getAllProducts();
    Product getProductById(int id);
    Boolean updateProduct(Product product);
    void deleteProduct(int id);
    Boolean addProduct(Product product);
}
