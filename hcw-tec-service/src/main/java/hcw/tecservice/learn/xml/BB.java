package hcw.tecservice.learn.xml;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/23 17:35
 * Description:
 * Others:
 */
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {
        "nnnnn","vvvvv"
})
public class BB {
    public String nnnnn;
    public String vvvvv;

    public String getNnnnn() {
        return nnnnn;
    }

    public void setNnnnn(String nnnnn) {
        this.nnnnn = nnnnn;
    }

    public String getVvvvv() {
        return vvvvv;
    }

    public void setVvvvv(String vvvvv) {
        this.vvvvv = vvvvv;
    }
}
