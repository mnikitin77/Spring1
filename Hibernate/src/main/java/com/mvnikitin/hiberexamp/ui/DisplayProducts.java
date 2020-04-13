package com.mvnikitin.hiberexamp.ui;

import com.mvnikitin.hiberexamp.domain.Product;

import java.util.List;

public class DisplayProducts implements DisplayData {
    @Override
    public void display(Object object) {
        List<Product> list = (List<Product>) object;
        for (Product p: list) {
            System.out.println(p);
        }
    }
}
