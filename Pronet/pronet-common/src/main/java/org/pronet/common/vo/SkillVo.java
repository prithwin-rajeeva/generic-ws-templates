package org.pronet.common.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pr250155 on 5/21/17.
 */
@XmlRootElement(name = "skill")
public class SkillVo extends BaseVo {

    private String skillName;
    private String skillDescription;
    private UserVo user;

    public String getSkillDescription() {
        return skillDescription;
    }
    @XmlElement
    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getSkillName() {
        return skillName;
    }

    @XmlElement
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public UserVo getUser() {
        return this.user;
    }

    @XmlElement
    public void setUser(UserVo user) {
        this.user = user;
    }
}
