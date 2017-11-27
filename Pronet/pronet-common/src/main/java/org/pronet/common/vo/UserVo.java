package org.pronet.common.vo;

/**
 * Created by pr250155 on 5/18/17.
 */


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "user")
public class  UserVo extends BaseVo{
    private String firstName;
    private String lastName;
    private String guid;
    private List<SkillVo> skills;

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGuid() {
        return guid;
    }

    @XmlElement
    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<SkillVo> getSkills(){
        return this.skills;
    }
    @XmlElement
    public void setSkills(List<SkillVo> skills) {
        this.skills = skills;
    }



}
