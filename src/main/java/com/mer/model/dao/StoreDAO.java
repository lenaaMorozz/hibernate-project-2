package com.mer.model.dao;

import com.mer.model.entity.movieRental.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends Dao<Store> {
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
