package cn.org.awcp.domain;

import cn.org.awcp.domain.base.BaseEntity;
import cn.org.awcp.domain.base.IEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test_student")
public class Student implements IEntity<Integer> {

    private static final long serialVersionUID = -5479415005369057246L;
    @Length(min = 2, max = 20, message = "姓名长度错误 by length")
    private String name;

    @Column(name = "class_id")
    @NotNull(message = "班级不能为空")
    private String classId;
    @Range(min = 0, max = 200, message = "年龄范围在0-200之间 by range")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Id
    @GeneratedValue()
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
