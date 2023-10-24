package com.mer.model.dao;

import com.mer.model.entity.movieRental.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends Dao<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
