package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

import java.util.HashMap;
import java.util.Map;

public class QueryManager {
    private HibernateExampleDataManager dataManager;
    Map<String, Query> executors = new HashMap<>();

    public QueryManager() {
        dataManager = new HibernateExampleDataManager();

        executors.put("/?", new HelpQuery());
        executors.put("add customer", new AddCustomer());
        executors.put("add product", new AddProduct());
        executors.put("add purchase", new AddPurchase());
        executors.put("get customers", new GetCustomers());
        executors.put("get products", new GetProducts());
        executors.put("get purchases", new GetPurchases());
        executors.put("remove customer", new RemoveCustomer());
        executors.put("remove product", new RemoveProduct());
        executors.put("remove purchase", new RemovePurchase());

        executors.get("/?").execute(null);
    }

    public void close() {
        dataManager.close();
    }

    public Object executeQuery(String queryCommand) {
        Query query = executors.get(queryCommand);
        try {
            return query.execute(dataManager);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid command");
        }
    }
}
