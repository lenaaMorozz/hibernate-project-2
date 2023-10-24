package com.mer.util;

import com.mer.model.entity.film.*;
import com.mer.model.entity.movieRental.*;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Data
public class UtilClass {
    private final SessionFactory sessionFactory;

    public UtilClass() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Film.class);
        configuration.addAnnotatedClass(FilmText.class);
        configuration.addAnnotatedClass(Language.class);
        configuration.addAnnotatedClass(Rating.class);
        configuration.addAnnotatedClass(SpecialFeatures.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Actor.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Staff.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Inventory.class);
        configuration.addAnnotatedClass(Rental.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
}
