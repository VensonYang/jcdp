package cn.org.jcdp.domain;

import cn.org.jcdp.domain.base.BaseEntity;
import cn.org.jcdp.domain.base.IEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "test_class")
public class StudentClass extends BaseEntity<String> {


    private static final long serialVersionUID = -7859825734000896158L;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "class_name")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
