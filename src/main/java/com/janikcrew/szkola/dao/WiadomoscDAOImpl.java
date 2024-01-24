package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Przedmiot;
import com.janikcrew.szkola.entity.Wiadomosc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WiadomoscDAOImpl implements WiadomoscDAO {

    private EntityManager entityManager;

    @Autowired
    public WiadomoscDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Wiadomosc wiadomosc) {
        entityManager.persist(wiadomosc);
    }

    @Override
    @Transactional
    public void update(Wiadomosc wiadomosc) {
        entityManager.merge(wiadomosc);
    }

    @Override
    @Transactional
    public void deleteWiadomoscById(int id) {
        Wiadomosc wiadomosc = entityManager.find(Wiadomosc.class, id);
        entityManager.remove(wiadomosc);
    }

    @Override
    public void deleteMessage(Wiadomosc wiadomosc) {
        entityManager.remove(wiadomosc);
    }

    @Override
    public Wiadomosc findWiadomoscById(int id) {
        return entityManager.find(Wiadomosc.class, id);
    }

    @Override
    public List<Wiadomosc> findWiadomosciOdebraneById(int id) {
        TypedQuery<Wiadomosc> query = entityManager.createQuery("from Wiadomosc where odbiorca.id = :data order by id desc", Wiadomosc.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<Wiadomosc> findWiadomosciWyslaneById(int id) {
        TypedQuery<Wiadomosc> query = entityManager.createQuery("from Wiadomosc where nadawca.id = :data order by id desc", Wiadomosc.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

}
