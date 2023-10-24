package com.mer.model.dao;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

@Getter
public class Dao<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public Dao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(int id) {
        final T entity = getById(id);
        getCurrentSession().delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
