package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Ocena;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OcenaDAOImpl implements OcenaDAO {

    private final EntityManager entityManager;

    @Autowired
    public OcenaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Ocena ocena) {
        entityManager.persist(ocena);
    }

    @Override
    @Transactional
    public void update(Ocena ocena) {
        entityManager.merge(ocena);
    }

    @Override
    public Ocena findOcenaById(int id) {
        return entityManager.find(Ocena.class, id);
    }

    @Override
    public List<Ocena> findOcenyByIdUcznia(int idUcznia, int idPrzedmiotu) {
        TypedQuery<Ocena> query = entityManager.createQuery("from Ocena where uczen.id = :idUcznia and przedmiot.id = :idPrzedmiotu order by id desc", Ocena.class);
        query.setParameter("idUcznia", idUcznia);
        query.setParameter("idPrzedmiotu", idPrzedmiotu);
        return query.getResultList();
    }

    @Override
    public List<Ocena> findOcenyByNazwaKlasy(String nazwaKlasy) {
        TypedQuery<Ocena> query = entityManager.createQuery("from Ocena where klasa.nazwa = :data", Ocena.class);
        query.setParameter("data", nazwaKlasy);
        return query.getResultList();
    }

    @Override
    public List<Ocena> findOcenyByNazwaKlasyAndIdNauczyciela(String nazwaKlasy, int idNauczyciela) {
        TypedQuery<Ocena> query = entityManager.createQuery("from Ocena where klasa.nazwa = :nazwaKlasyy and nauczyciel.id = :idNauczycielaa", Ocena.class);
        query.setParameter("nazwaKlasyy", nazwaKlasy);
        query.setParameter("idNauczycielaa", idNauczyciela);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteOcenaById(int id) {
        Ocena ocena = entityManager.find(Ocena.class, id);
        entityManager.remove(ocena);
    }
}
