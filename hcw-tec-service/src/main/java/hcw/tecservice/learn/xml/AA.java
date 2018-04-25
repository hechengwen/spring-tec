package hcw.tecservice.learn.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/23 17:14
 * Description:
 * Others:
 */
@XmlType(propOrder = {
        "sdf","page","bb"
})
@NoArgsConstructor
@AllArgsConstructor
public class AA{
    private int sdf;
    private String page;
    private BB bb;

    public int getSdf() {
        return sdf;
    }

    public void setSdf(int sdf) {
        this.sdf = sdf;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public BB getBb() {
        return bb;
    }

    public void setBb(BB bb) {
        this.bb = bb;
    }
}
