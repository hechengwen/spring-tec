package hcw.tecservice.learn.xml;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/20 16:54
 * Description:
 * Others:
 */
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "studentList")
public class StudentList {

    private List<Student> students;

    private List<Teacher> teachers;

    @XmlElementWrapper(name = "teachers")
    public List<Teacher> getTeacher() {
        return teachers;
    }

    public void setTeacher(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @XmlElementWrapper(name = "students")
    public List<Student> getStudent() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }
}
