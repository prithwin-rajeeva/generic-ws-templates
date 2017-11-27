package com.pronet.service.impl;

import com.pronet.eao.SkillEAO;
import com.pronet.entity.SkillEntity;
import com.pronet.mapper.SkillMapper;
import com.pronet.service.SkillService;
import org.pronet.common.vo.SkillVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by pr250155 on 5/21/17.
 */
@Service
public class SkillServiceImpl implements SkillService{

    @Resource
    private SkillEAO skillEAO;

    @Transactional
    public SkillVo addSkill(SkillVo skill) {
        SkillEntity skillEntity = SkillMapper.mapSkillVoToEntity(skill);
        return SkillMapper.mapEntityToVo(skillEAO.addSkill(skillEntity));
    }
}
