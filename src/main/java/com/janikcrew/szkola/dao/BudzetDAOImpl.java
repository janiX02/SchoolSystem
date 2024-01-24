package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Budzet;
import com.janikcrew.szkola.entity.Transakcja;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BudzetDAOImpl implements BudzetDAO {

    private EntityManager entityManager;

    @Autowired
    public BudzetDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Transakcja> findTransactionsByBudzetId(int id) {

        TypedQuery<Transakcja> query = entityManager.createQuery("from Transakcja where budzet.id = :data", Transakcja.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Budzet findBudzetById(int id) {
        return entityManager.find(Budzet.class, id);
    }

    @Override
    public Transakcja findTransakcjaById(int id) {
        return entityManager.find(Transakcja.class, id);
    }

    @Override
    @Transactional
    public void save(Budzet budzet) {
        entityManager.persist(budzet);
    }

    @Override
    @Transactional
    public void save(Transakcja transakcja) {
        entityManager.persist(transakcja);
    }

    @Override
    @Transactional
    public void update(Budzet budzet) {
        entityManager.merge(budzet);
    }

    @Override
    @Transactional
    public void update(Transakcja transakcja) {
        entityManager.merge(transakcja);
    }

    @Override
    @Transactional
    public void deleteTransactionById(int id) {
        Transakcja transakcja = entityManager.find(Transakcja.class, id);
        entityManager.remove(transakcja);
    }

    @Override
    @Transactional
    public void deleteBudzetById(int id) {
        Budzet budzet = entityManager.find(Budzet.class, id);
        entityManager.remove(budzet);
    }

    @Override
    @Transactional
    public void refresh(Budzet budzet) {
        entityManager.refresh(budzet);
    }
}
