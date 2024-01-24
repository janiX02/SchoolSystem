package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Wydarzenie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WydarzenieDAOImpl implements WydarzenieDAO {

    private EntityManager entityManager;

    @Autowired
    public WydarzenieDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Wydarzenie> findAllEvents() {
        TypedQuery<Wydarzenie> query = entityManager.createQuery("from Wydarzenie order by id desc", Wydarzenie.class);
        return query.getResultList();
    }

    @Override
    public Wydarzenie getEventById(int id) {
        return entityManager.find(Wydarzenie.class, id);
    }

    @Override
    @Transactional
    public void save(Wydarzenie wydarzenie) {
        entityManager.persist(wydarzenie);
    }

    @Override
    @Transactional
    public void update(Wydarzenie wydarzenie) {
        entityManager.merge(wydarzenie);
    }

    @Override
    @Transactional
    public void delete(Wydarzenie wydarzenie) {
        entityManager.remove(wydarzenie);
    }
}
