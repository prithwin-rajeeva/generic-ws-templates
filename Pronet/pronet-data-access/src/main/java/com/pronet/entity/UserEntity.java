package com.pronet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by pr250155 on 5/19/17.
 */
@Entity(name = "USER")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends GenericEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GUID")
    private String guid;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Set<SkillEntity> skillSet;
}
