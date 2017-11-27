package com.pronet.service.impl;

import com.pronet.eao.UserEAO;
import com.pronet.entity.UserEntity;
import com.pronet.mapper.UserMapper;
import com.pronet.service.UserService;
import org.pronet.common.vo.UserVo;
import org.springframework.stereotype.Service;
import static javax.transaction.Transactional.TxType.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Created by pr250155 on 5/19/17.
 */

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name="userEAOImpl")
    private UserEAO userEAO;

    public UserVo getUser(int userId) {
        UserEntity userEntity = userEAO.getUserEntity(userId);
        return UserMapper.mapUserEntityToVo(userEntity);
    }

    @Transactional(REQUIRES_NEW)
    public UserVo addUser(UserVo user) {
        user.setGuid(UUID.randomUUID().toString());
        return  UserMapper.mapUserEntityToVo(
                userEAO.save(
                        UserMapper.mapUserVoToEntity(user)));
    }
}
