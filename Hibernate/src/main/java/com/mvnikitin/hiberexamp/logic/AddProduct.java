package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddProduct implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("enter the product's data " +
                "(or type \"cancel\" to quit). ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.println("enter the product's name: ");
            String name = reader.readLine();
            if(name.equals("cancel")) {
                return null;
            }
            System.out.println("enter the product's price: ");
            String price = reader.readLine();
            if(price.equals("cancel")) {
                return null;
            }

            return "The product added successfully, id = " +
                    dataManager.addProduct(name, Double.parseDouble(price));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
