package com.mvnikitin.sprdata.services;

import com.mvnikitin.sprdata.entities.Product;
import com.mvnikitin.sprdata.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final static int DEFAULT_PAGE_SIZE = 5;

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findById(Optional <Long> id) {
        Pageable pageable = PageRequest.of(0, 1);
        if (id.isPresent()) {
            return productRepository.findById(id.get(), pageable);
        }
        return null;
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
    public Page<Product> findByPage(
            Optional<Double> priceMin,
            Optional<Double> priceMax,
            Optional<Integer> pageNumber,
            Optional<Integer> rowsPerPage) {

        Pageable pageable = PageRequest.of(
                pageNumber.isPresent() ? pageNumber.get() - 1 : 0,
                rowsPerPage.orElse(DEFAULT_PAGE_SIZE));

        if (!priceMin.isPresent() && !priceMax.isPresent()) {
            return productRepository.findAll(pageable);
        } else if (!priceMin.isPresent()) {
            return productRepository.findByPriceLessThan(priceMax.get(), pageable);
        } else if (!priceMax.isPresent()) {
            return productRepository.findByPriceGreaterThan(
                    priceMin.get(), pageable);
        }
        return productRepository.findByPriceBetween(
                priceMin.get(), priceMax.get(), pageable);
    }
}
