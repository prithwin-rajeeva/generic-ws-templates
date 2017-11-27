package com.pronet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by pr250155 on 5/21/17.
 */
@Entity(name = "SKILL")
@Data
@EqualsAndHashCode(callSuper = false)
public class SkillEntity extends GenericEntity {
    @Column(name = "SKILL_NAME")
    private String skillName;

    @Column(name = "SKILL_DESCRIPTION")
    private String skillDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

}
