package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

public class RemoveClient implements Query {
    @Override
    public Object execute(HibernateExampleDataManager dataManager) {
        System.out.println(this.getClass().getSimpleName());
        return null;
    }
}
