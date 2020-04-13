package com.mvnikitin.hiberexamp.ui;

import com.mvnikitin.hiberexamp.logic.*;

import java.util.HashMap;
import java.util.Map;

public class DisplayManager {
    Map<String, DisplayData> displays = new HashMap<>();

    public DisplayManager() {
        DisplayCustomers displayCustomers = new DisplayCustomers();
        DisplayProducts displayProducts = new DisplayProducts();
        DisplayPurchases displayPurchases = new DisplayPurchases();
        DisplayString displayString = new DisplayString();

        displays.put("/?", displayString);
        displays.put("add customer", displayString);
        displays.put("add product", displayString);
        displays.put("add purchase", displayString);
        displays.put("get customers", displayCustomers);
        displays.put("get products", displayProducts);
        displays.put("get purchases", displayPurchases);
        displays.put("remove client", displayString);
        displays.put("remove product", displayString);
        displays.put("remove purchase", displayString);
    }

    public void display(String queryCommand, Object data) {
        DisplayData displayData = displays.get(queryCommand);
        try {
            displayData.display(data);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid command");
        }
    }
}
