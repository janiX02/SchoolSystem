package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Przedmiot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class PrzedmiotDAOImpl implements PrzedmiotDAO {

    private EntityManager entityManager;

    @Autowired
    public PrzedmiotDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Przedmiot> findPrzedmiotyByNauczycielId(int id) {
        TypedQuery<Przedmiot> query = entityManager.createQuery("from Przedmiot where prowadzacy.id = :data", Przedmiot.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<Przedmiot> findSubjectsByTeacherIdAndClassName(int idNauczyciela, String nazwaKlasy) {
        TypedQuery<Przedmiot> query = entityManager.createQuery("from Przedmiot where prowadzacy.id = :idNauczyciela and klasa.nazwa = :nazwaKlasy", Przedmiot.class);
        query.setParameter("idNauczyciela", idNauczyciela);
        query.setParameter("nazwaKlasy", nazwaKlasy);
        return query.getResultList();
    }

    @Override
    public List<Przedmiot> findSubjectsByClassName(String nazwaKlasy) {
        TypedQuery<Przedmiot> query = entityManager.createQuery("from Przedmiot where klasa.nazwa = :nazwa", Przedmiot.class);
        query.setParameter("nazwa", nazwaKlasy);
        return query.getResultList();
    }

    @Override
    public Przedmiot findPrzedmiotById(int id) {
        return entityManager.find(Przedmiot.class, id);
    }

    @Override
    @Transactional
    public void deletePrzedmiotById(int id) {
        Przedmiot przedmiot = entityManager.find(Przedmiot.class, id);
        entityManager.remove(przedmiot);
    }

    @Override
    @Transactional
    public void save(Przedmiot przedmiot) {
        entityManager.persist(przedmiot);
    }

    @Override
    @Transactional
    public void update(Przedmiot przedmiot) {
        entityManager.merge(przedmiot);
    }
}
