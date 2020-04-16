package com.mvnikitin.mvcexamp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(com.mvnikitin.mvcexamp.model.Product product) {
        productRepository.add(product);
    }

    public com.mvnikitin.mvcexamp.model.Product remove (int id) {
        return productRepository.remove(id);
    }

    public List<com.mvnikitin.mvcexamp.model.Product> getProducts() {
        return productRepository.getProducts();
    }

    public List<Product>  getProduct (int id) {
        return productRepository.getProduct(id);
    }
}
