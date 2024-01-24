package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Dzien;
import com.janikcrew.szkola.entity.GodzinaLekcyjna;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class KalendarzDAOImpl implements KalendarzDAO {
    private EntityManager entityManager;

    @Autowired
    public KalendarzDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Dzien dzien) {
        entityManager.persist(dzien);
    }

    @Override
    public List<Dzien> getKalendarzByDzien(String dzien) {
        dzien = dzien.toLowerCase();
        TypedQuery<Dzien> query = entityManager.createQuery("from Dzien where nazwaDnia = :data", Dzien.class);
        query.setParameter("data", dzien);
        return query.getResultList();
    }

    @Override
    public List<Dzien> getKalendarz() {
        TypedQuery<Dzien> query = entityManager.createQuery("from Dzien", Dzien.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteDzienByDate(LocalDate date) {
        Dzien dzien = entityManager.find(Dzien.class, date);
        entityManager.remove(dzien);
    }
}
