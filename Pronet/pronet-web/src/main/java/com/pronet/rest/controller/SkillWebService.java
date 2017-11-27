package com.pronet.rest.controller;

import com.pronet.service.SkillService;
import org.pronet.common.vo.SkillVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by pr250155 on 5/21/17.
 */
@RestController
@RequestMapping(value = "/skill")
public class SkillWebService {

    @Resource
    private SkillService skillService;

    @RequestMapping(method = RequestMethod.PUT , consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public SkillVo addSkillForUser(@RequestBody SkillVo skill) {
        return skillService.addSkill(skill);
    }

}
