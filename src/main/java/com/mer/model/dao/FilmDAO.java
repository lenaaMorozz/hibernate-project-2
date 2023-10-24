package com.mer.model.dao;

import com.mer.model.entity.film.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends Dao<Film>{

    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
