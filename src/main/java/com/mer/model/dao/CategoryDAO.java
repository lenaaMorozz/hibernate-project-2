package com.mer.model.dao;

import com.mer.model.entity.film.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends Dao<Category> {

    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
