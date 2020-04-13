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
                int result = dataManager.addCustomer(userInput);
                return (result > 0) ?
                        "the customer added successfully, id = " + result :
                        "the operation failed";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
