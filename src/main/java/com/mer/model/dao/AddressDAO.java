package com.mer.model.dao;

import com.mer.model.entity.movieRental.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends Dao<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
