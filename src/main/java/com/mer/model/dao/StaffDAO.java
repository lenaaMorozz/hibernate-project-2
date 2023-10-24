package com.mer.model.dao;

import com.mer.model.entity.movieRental.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends Dao<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
