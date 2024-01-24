package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Nauczyciel;
import com.janikcrew.szkola.entity.Osoba;
import com.janikcrew.szkola.entity.Rodzic;
import com.janikcrew.szkola.entity.Uczen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OsobaDAOImpl implements OsobaDAO {

    private EntityManager entityManager;

    @Autowired
    public OsobaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Osoba osoba) {
        entityManager.persist(osoba);
    }

    @Override
    @Transactional
    public void update(Osoba osoba) {
        entityManager.merge(osoba);
    }

    @Override
    public Osoba findOsobaById(int id) {
        return entityManager.find(Osoba.class, id);
    }

    @Override
    public Osoba findOsobaByEmail(String email) {
        TypedQuery<Osoba> query = entityManager.createQuery("SELECT o FROM osoba o WHERE o.email = :data", Osoba.class);
        query.setParameter("data", email);
        return query.getSingleResult();
    }

    @Override
    public List<Uczen> findStudentsByClassName(String nazwaKlasy) {
        TypedQuery<Uczen> query = entityManager.createQuery("from osoba where klasa.nazwa = :data order by nazwisko, imie", Uczen.class);
        query.setParameter("data", nazwaKlasy);
        return query.getResultList();
    }

    @Override
    public List<Nauczyciel> findAllTeachers() {
        Query query = entityManager.createNativeQuery("SELECT * FROM osoba WHERE rola = 'ROLE_NAUCZYCIEL' order by nazwisko, imie", Osoba.class);

        return query.getResultList();
    }

    @Override
    public List<Uczen> findNotAcceptedStudents() {
        TypedQuery<Uczen> query = entityManager.createQuery("from Uczen where accepted=0 order by id desc", Uczen.class);
        return query.getResultList();
    }

    @Override
    public List<Rodzic> findNotAcceptedParents() {
        TypedQuery<Rodzic> query = entityManager.createQuery("from Rodzic where accepted=0 order by id desc", Rodzic.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteOsobaById(int id) {
        Osoba osoba = entityManager.find(Osoba.class, id);
        entityManager.remove(osoba);
    }
}
