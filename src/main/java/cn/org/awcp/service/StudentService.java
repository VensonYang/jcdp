package cn.org.awcp.service;

import cn.org.awcp.domain.Student;
import cn.org.awcp.service.base.BaseService;

import java.util.List;

public interface StudentService extends BaseService<Student, Integer> {

    List query();

    Object queryForObject(Integer id);

    void executeUpdate(String name);
}
