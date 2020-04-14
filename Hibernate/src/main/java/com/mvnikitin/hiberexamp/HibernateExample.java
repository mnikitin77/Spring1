package com.mvnikitin.hiberexamp;

import com.mvnikitin.hiberexamp.logic.QueryManager;
import com.mvnikitin.hiberexamp.ui.DisplayManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HibernateExample {
    public static void main(String[] args) throws IOException {
        QueryManager queryManager = new QueryManager();
        DisplayManager displayManager = new DisplayManager();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println();
        displayManager.display("/?", queryManager.executeQuery("/?"));

        while (true) {
            System.out.println("Enter your query: ");
            try {
                String command = reader.readLine();
                if(command.equals("exit")) {
                    break;
                }
                Object result = queryManager.executeQuery(command);
                if(result != null) {
                    displayManager.display(command, result);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }

        System.out.println("Thank you! See you next time, goodbye.");
        System.out.println();

        queryManager.close();
        reader.close();
    }
}
