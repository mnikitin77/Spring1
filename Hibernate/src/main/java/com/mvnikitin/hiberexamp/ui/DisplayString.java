package com.mvnikitin.hiberexamp.ui;

public class DisplayString implements DisplayData {
    @Override
    public void display(Object object) {
        System.out.println((String)object);
    }
}
