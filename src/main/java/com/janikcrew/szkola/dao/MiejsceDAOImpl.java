package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Miejsce;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MiejsceDAOImpl implements MiejsceDAO {
    private final EntityManager entityManager;

    @Autowired
    public MiejsceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Miejsce findMiejsceById(int id) {
        return entityManager.find(Miejsce.class, id);
    }

    @Override
    public List<Miejsce> findAllMiejsca() {
        TypedQuery<Miejsce> query = entityManager.createQuery("from Miejsce", Miejsce.class);
        return query.getResultList();
    }

    @Override
    public List<Miejsce> findMiejsceByNazwa(String nazwa) {
        TypedQuery<Miejsce> query = entityManager.createQuery("from Miejsce where nazwa = :data", Miejsce.class);
        query.setParameter("data", nazwa);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Miejsce miejsce) {
        entityManager.persist(miejsce);
    }

    @Override
    @Transactional
    public void update(Miejsce miejsce) {
        entityManager.merge(miejsce);
    }

    @Override
    @Transactional
    public void deleteMiejsceById(int id) {
        Miejsce miejsce = entityManager.find(Miejsce.class, id);
        entityManager.remove(miejsce);
    }
}
