package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.GodzinaLekcyjna;
import com.janikcrew.szkola.entity.Przedmiot;
import com.janikcrew.szkola.entity.Wiadomosc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GodzinaLekcyjnaDAOImpl implements GodzinaLekcyjnaDAO {
    private EntityManager entityManager;

    @Autowired
    public GodzinaLekcyjnaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public GodzinaLekcyjna getGodzinaLekcyjnaById(int id) {
        return entityManager.find(GodzinaLekcyjna.class, id);
    }

    @Override
    public List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByKlasaName(String name) {
        TypedQuery<GodzinaLekcyjna> query = entityManager.createQuery("from GodzinaLekcyjna where klasa.nazwa = :data", GodzinaLekcyjna.class);
        query.setParameter("data", name);
        return query.getResultList();
    }

    @Override
    public List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByNauczycielId(int id) {
        TypedQuery<GodzinaLekcyjna> query = entityManager.createQuery("from GodzinaLekcyjna where nauczyciel.id = :data " +
                " or nauczycielZastepujacy.id = :data", GodzinaLekcyjna.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByPrzedmiotId(int id) {
        TypedQuery<GodzinaLekcyjna> query = entityManager.createQuery("from GodzinaLekcyjna where przedmiot.id = :data", GodzinaLekcyjna.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<GodzinaLekcyjna> getListOfGodzinaLekcyjnaByMiejsceId(int id) {
        TypedQuery<GodzinaLekcyjna> query = entityManager.createQuery("from GodzinaLekcyjna where miejsce.id = :data", GodzinaLekcyjna.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(GodzinaLekcyjna godzinaLekcyjna) {
        entityManager.persist(godzinaLekcyjna);
    }

    @Override
    @Transactional
    public void update(GodzinaLekcyjna godzinaLekcyjna) {
        entityManager.merge(godzinaLekcyjna);
    }

    @Override
    @Transactional
    public void deleteGodzinaLekcyjnaById(int id) {
        GodzinaLekcyjna godzinaLekcyjna = entityManager.find(GodzinaLekcyjna.class, id);
        entityManager.remove(godzinaLekcyjna);
    }
}
