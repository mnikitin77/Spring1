package com.mvnikitin.hiberexamp.logic;

import com.mvnikitin.hiberexamp.dao.HibernateExampleDataManager;

public interface Query {
    Object execute(HibernateExampleDataManager dataManager);
}
