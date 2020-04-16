package com.mvnikitin.sprdata.services;

import com.mvnikitin.sprdata.entities.Product;
import com.mvnikitin.sprdata.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getById(Long id) {
        List<Product> list = new ArrayList<>();
        list.add(productRepository.findById(id).orElse(null));
        return list;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByPriceBetween(double priceMin, double priceMax) {
        return productRepository.findByPriceBetween(priceMin, priceMax);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public long countByPriceBetween(double priceMin, double priceMax) {
        return productRepository.countByPriceBetween(priceMin, priceMax);
    }

    @Override
    public PageInfo findByPage(int pageNumber, int rowsPerPage) {
        return new PageInfo(productRepository
                .findAll(PageRequest.of(pageNumber, rowsPerPage)));
    }

    @Override
    public PageInfo findByPage(int pageNumber, int rowsPerPage, double priceMin, double priceMax) {
        return new PageInfo(productRepository
                .findByPriceBetween(priceMin, priceMax, PageRequest.of(pageNumber, rowsPerPage)));
    }
}
