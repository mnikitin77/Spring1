package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

public class HelpQuery implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        String result =
                "********************************************************************************\n" +
                "* Queries:                                                                     *\n" +
                "*                                                                              *\n" +
                "* /? - help                                                                    *\n" +
                "* add customer - add a new customer                                            *\n" +
                "* add product - add a new product                                              *\n" +
                "* add purchase - add a purchase to a customer                                  *\n" +
                "* get customers - get all customers or a customer                              *\n" +
                "* get products - get all products or a product                                 *\n" +
                "* get purchases - get all purchases, a customer's purchases, a purchase by id  *\n" +
                "* remove customer - remove a customer by id                                    *\n" +
                "* remove product - remove a product                                            *\n" +
                "* remove purchase - get all purchases of a customer                            *\n" +
                "* cancel - cancel the current command (if applicable)                          *\n" +
                "* exit - close the program                                                     *\n" +
                "********************************************************************************\n";
//        System.out.println(result);

        return result;
    }
}
