package hcw.tecservice.learn.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/21 11:05
 * Description:
 * Others:
 */
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Teacher")
@XmlType(propOrder = {
        "name","sex","number","className","hobby","aa"
})
public class Teacher {
    private String name;  //姓名

    private String sex;    //性别

    private int number;     //学号

    private String className;    //班级

    private List<String> hobby;    //爱好

    private List<AA> aa;

    @XmlElementWrapper(name = "aas")
    public List<AA> getAa() {
        return aa;
    }

    public void setAa(List<AA> aa) {
        this.aa = aa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElementWrapper(name = "hobbyList")
    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

}
