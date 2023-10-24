package com.mer.model.dao;

import com.mer.model.entity.film.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends Dao<Language>{

    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
