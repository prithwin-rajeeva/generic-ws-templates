package com.pronet.eao.impl;

import com.pronet.eao.UserEAO;
import com.pronet.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pr250155 on 5/19/17.
 */
@Repository("userEAOImpl")
public class UserEAOImpl extends GenericEAOImpl<UserEntity> implements UserEAO {

    public UserEntity getUserEntity(int userId) {
        return manager.find(UserEntity.class,userId);
    }
}
