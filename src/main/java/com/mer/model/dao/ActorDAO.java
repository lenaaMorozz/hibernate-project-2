package com.mer.model.dao;

import com.mer.model.entity.film.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends Dao<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
