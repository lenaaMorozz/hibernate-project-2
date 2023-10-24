package com.mer.model.dao;

import com.mer.model.entity.movieRental.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends Dao<Country> {

    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
