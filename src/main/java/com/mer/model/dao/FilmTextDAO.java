package com.mer.model.dao;

import com.mer.model.entity.film.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends Dao<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
