package com.pronet.mapper;

import com.pronet.entity.SkillEntity;
import com.pronet.entity.UserEntity;
import org.pronet.common.vo.SkillVo;
import org.pronet.common.vo.UserVo;

/**
 * Created by pr250155 on 5/21/17.
 */
public class SkillMapper {
    public static SkillEntity mapSkillVoToEntity(SkillVo skill) {
        SkillEntity skillEntity = new SkillEntity();
        UserEntity userEntity = new UserEntity();
        skillEntity.setSkillDescription(skill.getSkillDescription());
        skillEntity.setSkillName(skill.getSkillName());
        userEntity.setFirstName(skill.getUser().getFirstName());
        userEntity.setLastName(skill.getUser().getLastName());
        userEntity.setGuid(skill.getUser().getGuid());
        userEntity.setId(skill.getUser().getId());
        skillEntity.setUser(userEntity);
        return skillEntity;
    }

    public static SkillVo mapEntityToVo(SkillEntity skillEntity) {
        SkillVo skillVo = new SkillVo();
        UserVo userVo = new UserVo();
        userVo.setFirstName(skillEntity.getUser().getFirstName());
        userVo.setLastName(skillEntity.getUser().getLastName());
        userVo.setGuid(skillEntity.getUser().getGuid());
        userVo.setId(skillEntity.getUser().getId());
        skillVo.setUser(userVo);
        skillVo.setSkillDescription(skillEntity.getSkillDescription());
        skillVo.setSkillName(skillEntity.getSkillName());
        return skillVo;
    }
}
