package org.pronet.common.vo;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pr250155 on 5/21/17.
 */

public class BaseVo {
    private int id;

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
