package com.mer.model.dao;

import com.mer.model.entity.movieRental.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends Dao<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
