package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetProducts implements Query{
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("type \"all\" or enter the product " +
                "id\n(- to quit the command type \"cancel\")");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            if(userInput.equals("cancel")) {
                return null;
            } else if (userInput.equals("all")) {
                return dataManager.getProducts();
            } else {
                return dataManager.getProduct(Long.parseLong(userInput));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
