package com.mvnikitin.sprdata.services;

import com.mvnikitin.sprdata.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
//    List<Product> getById(Long id);
    void save(Product product);
    void remove(Long id);

    Page<Product> findById(Optional <Long> id);

    Page<Product> findByPage(Optional<Double> priceMin,
                             Optional<Double> priceMax,
                             Optional<Integer> pageNumber,
                             Optional<Integer> rowsPerPage);
}
