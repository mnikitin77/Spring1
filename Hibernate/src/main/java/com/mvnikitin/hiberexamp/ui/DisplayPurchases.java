package com.mvnikitin.hiberexamp.ui;

import com.mvnikitin.hiberexamp.domain.Purchase;

import java.util.List;

public class DisplayPurchases implements DisplayData {
    @Override
    public void display(Object object) {
        List<Purchase> list = (List<Purchase>) object;
        for (Purchase p: list) {
            System.out.println(p);
        }
    }
}
