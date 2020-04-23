package com.mvnikitin.boot.dao;

import com.mvnikitin.boot.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findById(long id, Pageable pageable);
    Page<Product> findByPriceBetween(double priceMin,
                                     double priceMax,
                                     Pageable pageable);
    Page<Product> findByPriceGreaterThan(double priceMin,
                                         Pageable pageable);
    Page<Product> findByPriceLessThan(double priceMax,
                                         Pageable pageable);
}
