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

    @Column(name = "teacher_id")
    private Long teacher;

    @Column(name = "class_name")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }
}
