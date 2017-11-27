package com.pronet.eao.impl;

import com.pronet.eao.SkillEAO;
import com.pronet.entity.SkillEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by pr250155 on 5/21/17.
 */
@Repository
public class SkillEAOImpl extends GenericEAOImpl<SkillEntity> implements SkillEAO {

    public SkillEntity addSkill(SkillEntity skillEntity) {
        return save(skillEntity);
    }
}
