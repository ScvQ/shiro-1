package cn.superid.dao.impl;

import cn.superid.dao.IBaseDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
@Component
public abstract class BaseDAOSupport<T> implements IBaseDAO<T> {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDAOSupport() {
        // try to get class
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }


    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(int id) {
        return (T) getSession().get(persistentClass, id);
    }

    @Override
    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }

    @Override
    public List<T> findByCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
}
