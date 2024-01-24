package com.janikcrew.szkola.dao;

import com.janikcrew.szkola.entity.Dyzur;
import com.janikcrew.szkola.entity.GodzinaLekcyjna;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DyzurDAOImpl implements DyzurDAO {

    private final EntityManager entityManager;

    @Autowired
    public DyzurDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Dyzur getDyzurById(int id) {
        return entityManager.find(Dyzur.class, id);
    }

    @Override
    public List<Dyzur> getListOfDyzurByNauczycielId(int id) {
        TypedQuery<Dyzur> query = entityManager.createQuery("from Dyzur where nauczyciel.id = :data " +
                " or nauczycielZastepujacy.id = :data", Dyzur.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public List<Dyzur> getAll() {
        TypedQuery<Dyzur> query = entityManager.createQuery("from Dyzur", Dyzur.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Dyzur dyzur) {
        entityManager.persist(dyzur);
    }

    @Override
    @Transactional
    public void update(Dyzur dyzur) {
        entityManager.merge(dyzur);
    }

    @Override
    @Transactional
    public void deleteDyzurById(int id) {
        Dyzur dyzur = entityManager.find(Dyzur.class, id);
        entityManager.remove(dyzur);
    }
}
