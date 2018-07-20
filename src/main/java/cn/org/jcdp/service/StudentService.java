package cn.org.jcdp.service;

import cn.org.jcdp.domain.Student;
import cn.org.jcdp.service.base.BaseService;

import java.util.List;

public interface StudentService extends BaseService<Student, Integer> {

    List query();

    Object queryForObject(Integer id);

    void executeUpdate(String name);
}
