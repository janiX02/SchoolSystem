package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Uwaga;
import com.janikcrew.szkola.entity.Wiadomosc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UwagaDAOImpl implements UwagaDAO {
    private EntityManager entityManager;

    @Autowired
    public UwagaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Uwaga uwaga) {
        entityManager.persist(uwaga);
    }

    @Override
    @Transactional
    public void update(Uwaga uwaga) {
        entityManager.merge(uwaga);
    }

    @Override
    @Transactional
    public void deleteUwagaById(int id) {
        Uwaga uwaga = entityManager.find(Uwaga.class, id);
        entityManager.remove(uwaga);
    }

    @Override
    public Uwaga findUwagaById(int id) {
        return entityManager.find(Uwaga.class, id);
    }

    @Override
    public List<Uwaga> findUwagiWystawioneByIdNauczyciela(int id) {
        TypedQuery<Uwaga> query = entityManager.createQuery("from Uwaga where wystawiajacy.id = :data order by id desc", Uwaga.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<Uwaga> findUwagiWystawioneByIdUcznia(int id) {
        TypedQuery<Uwaga> query = entityManager.createQuery("from Uwaga where otrzymujacy.id = :data order by id desc", Uwaga.class);
        query.setParameter("data", id);
        return query.getResultList();
    }
}
