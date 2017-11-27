package com.pronet.eao;

import com.pronet.entity.UserEntity;

/**
 * Created by pr250155 on 5/19/17.
 */
public interface UserEAO {
    public UserEntity getUserEntity(int userId);
    UserEntity save(UserEntity userEntity);
}
