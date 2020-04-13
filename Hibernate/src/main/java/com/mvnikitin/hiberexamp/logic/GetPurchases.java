package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetPurchases implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("type either \"all\" or \"customer\" " +
                "or enter the purchase id\n" +
                "(- to quit the command type \"cancel\")");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            if(userInput.equals("cancel")) {
                return null;
            } else if (userInput.equals("all")) {
                return dataManager.getPurchases();
            } else if (userInput.equals("customer")) {
                System.out.println("enter the customer's id: ");
                int customerId = Integer.parseInt(reader.readLine());
                return dataManager.getPurchases(customerId);
            } else {
                // query by id
                return dataManager.getPurchase(Integer.parseInt(userInput));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
