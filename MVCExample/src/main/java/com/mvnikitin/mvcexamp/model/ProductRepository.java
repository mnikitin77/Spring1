package com.mvnikitin.mvcexamp.model;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {
    private AtomicInteger idCounter = new AtomicInteger(10000000);
    Map<Integer, Product> products = new HashMap<>();

    public ProductRepository() {
        add(new Product("Гречка \"Мистраль\", пакет 500г.", 65f));
        add(new Product("Макаронные изделия \"Тоскана\", коробка 500г.", 199f));
        add(new Product("Бумага туалетная \"54 метра\", рулон", 54f));
    }

    public void add(Product product) {
        product.setId(idCounter.incrementAndGet());
        products.put(product.getId(), product);
    }

    public Product remove (int id) {
        return products.remove(id);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList(products.values()));
    }

    public List<Product> getProduct (int id) {
        List<Product> list = new ArrayList<>();
        list.add(products.get(id));
        return list;
    }
}
