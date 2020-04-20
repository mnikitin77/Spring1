package com.mvnikitin.sprdata.repositories;

import com.mvnikitin.sprdata.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
