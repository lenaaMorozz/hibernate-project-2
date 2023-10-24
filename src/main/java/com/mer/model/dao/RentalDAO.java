package com.mer.model.dao;

import com.mer.model.entity.movieRental.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends Dao<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class,sessionFactory);
    }
}
