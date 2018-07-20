package cn.org.jcdp.domain;

import cn.org.jcdp.domain.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test_teacher")
public class Teacher extends BaseEntity<Long> {

    private static final long serialVersionUID = -7859825734000896158L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
