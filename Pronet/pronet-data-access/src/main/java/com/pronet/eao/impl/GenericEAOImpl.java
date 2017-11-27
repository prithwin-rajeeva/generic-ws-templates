package com.pronet.eao.impl;

import com.pronet.entity.GenericEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pr250155 on 5/21/17.
 */
@Component
public class GenericEAOImpl<E extends GenericEntity> {

    @PersistenceContext
    protected EntityManager manager;

    public E save(E e) {
        manager.persist(e);
        return e;
    }
}
