package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddCustomer implements Query{
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("enter the customer's name: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            if(userInput.equals("cancel")) {
                return null;
            } else {
                return "The customer added successfully, id = " +
                        dataManager.addCustomer(userInput);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
