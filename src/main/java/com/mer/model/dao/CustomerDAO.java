package com.mer.model.dao;

import com.mer.model.entity.movieRental.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends Dao<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
