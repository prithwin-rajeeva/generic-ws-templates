package com.pronet.mapper;

import com.pronet.entity.SkillEntity;
import com.pronet.entity.UserEntity;
import org.pronet.common.vo.SkillVo;
import org.pronet.common.vo.UserVo;

import java.util.ArrayList;

/**
 * Created by pr250155 on 5/19/17.
 */
public class UserMapper {
    public static UserVo mapUserEntityToVo(UserEntity userEntity){
         UserVo user = new UserVo();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setGuid(userEntity.getGuid());
        user.setSkills(new ArrayList());
        for (SkillEntity skillEntity : userEntity.getSkillSet()) {
            SkillVo skill = new SkillVo();
            skill.setSkillDescription(skillEntity.getSkillDescription());
            skill.setSkillName(skillEntity.getSkillName());
            user.getSkills().add(skill);
        }
        return user;
    }

    public static UserEntity mapUserVoToEntity(UserVo user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setGuid(user.getGuid());
        return userEntity;
    }
}
