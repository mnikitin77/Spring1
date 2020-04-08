package com.mvnikitin.mvcexamp.model;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    Map<Integer, Product> products = new HashMap<>();

    public ProductRepository() {
        Product product = new Product("Гречка \"Мистраль\", пакет 500г.", 65f);
        products.put(product .getId(), product);
        product = new Product("Макаронные изделия \"Тоскана\", коробка 500г.", 199f);
        products.put(product .getId(), product);
        product = new Product("Бумага туалетная \"54 метра\", рулон", 54f);
        products.put(product .getId(), product);
    }

    public void add(Product product) {
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
