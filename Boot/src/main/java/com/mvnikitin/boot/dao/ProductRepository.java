package com.mvnikitin.boot.dao;

import com.mvnikitin.boot.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findById(Long id, Pageable pageable);
    Optional<Product> findById(Long id);
    Page<Product> findByPriceBetween(Double priceMin,
                                     Double priceMax,
                                     Pageable pageable);
    Page<Product> findByPriceGreaterThan(Double priceMin,
                                         Pageable pageable);
    Page<Product> findByPriceLessThan(Double priceMax,
                                         Pageable pageable);
}
