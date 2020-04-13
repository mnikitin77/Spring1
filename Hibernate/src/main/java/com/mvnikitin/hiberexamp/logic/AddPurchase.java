package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddPurchase implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("enter the customers's id " +
                "(or type \"cancel\" to quit): ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            if(userInput.equals("cancel")) {
                return null;
            }
            int customerId = Integer.parseInt(userInput);
            List<Integer> productIds = new ArrayList<>();

            System.out.println("enter the product's id one by one,\n" +
                    "to finish type \"ok\" (or \"cancel\" to quit): ");
            
            while (true) {
                userInput = reader.readLine();
                if (userInput.equals("cancel")) {
                    return null;
                }
                if (userInput.equals("ok")) {
                    break;
                }
                productIds.add(Integer.parseInt(userInput));
            }
            return "The purchase added successfully, id = " +
                    dataManager.addPurchase(customerId, productIds);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
