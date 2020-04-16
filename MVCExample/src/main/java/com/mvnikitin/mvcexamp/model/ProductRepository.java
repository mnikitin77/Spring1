package com.mvnikitin.mvcexamp.model;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {
    private AtomicInteger idCounter = new AtomicInteger(10000000);
    Map<Integer, com.mvnikitin.mvcexamp.model.Product> products = new HashMap<>();

    public ProductRepository() {
        add(new com.mvnikitin.mvcexamp.model.Product("Гречка \"Мистраль\", пакет 500г.", 65f));
        add(new com.mvnikitin.mvcexamp.model.Product("Макаронные изделия \"Тоскана\", коробка 500г.", 199f));
        add(new com.mvnikitin.mvcexamp.model.Product("Бумага туалетная \"54 метра\", рулон", 54f));
    }

    public void add(com.mvnikitin.mvcexamp.model.Product product) {
        product.setId(idCounter.incrementAndGet());
        products.put(product.getId(), product);
    }

    public com.mvnikitin.mvcexamp.model.Product remove (int id) {
        return products.remove(id);
    }

    public List<com.mvnikitin.mvcexamp.model.Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList(products.values()));
    }

    public List<com.mvnikitin.mvcexamp.model.Product> getProduct (int id) {
        List<Product> list = new ArrayList<>();
        list.add(products.get(id));
        return list;
    }
}
