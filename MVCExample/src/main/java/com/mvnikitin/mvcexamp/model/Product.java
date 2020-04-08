package com.mvnikitin.mvcexamp.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static AtomicInteger idCounter = new AtomicInteger(10000000);

    private int id;
    private String name;
    private float price;

    public Product() {
    }

    public Product(String name, float price) {
        setId();
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        // Костыль. По каким-то причинам объект создаётся воторой раз при
        // обработке POST-запроса от формы. Поэтому при присвоении id в
        // конструкторе, у каждого следующего элемента коллекции он
        // увеличивается на 2. Из-за этого такой обходной путь.
        this.name = name;
        if (id == 0) {
            setId();
        }
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId() {
        this.id = id = idCounter.incrementAndGet();;
    }
}
