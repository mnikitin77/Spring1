package com.mvnikitin.hiberexamp.ui;

import com.mvnikitin.hiberexamp.domain.Customer;

import java.util.List;

public class DisplayCustomers implements DisplayData {
    @Override
    public void display(Object object) {
        List<Customer> list = (List<Customer>) object;
        for (Customer c: list) {
            System.out.println(c);
        }
    }
}
