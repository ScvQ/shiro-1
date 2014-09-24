package cn.superid.dao;

import org.hibernate.criterion.Criterion;

import java.util.List;

public interface IBaseDAO<T> {
    void save(T t);

    void update(T t);

    void delete(T t);

    void saveOrUpdate(T t);

    T findById(int id);

    List<T> findByCriteria(Criterion... criterions);
}
