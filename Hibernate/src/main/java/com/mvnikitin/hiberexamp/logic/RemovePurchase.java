package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemovePurchase implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println("enter the purchase's id\n" +
                "(- to quit the command type \"cancel\")");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String userInput = reader.readLine();
            if(userInput.equals("cancel")) {
                return null;
            } else {
                return (dataManager.removePurchase(Long
                        .parseLong(userInput))) ?
                        "the purchase successfully deleted" :
                        "the operation failed";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
