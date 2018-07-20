package cn.org.awcp.service.impl;

import cn.org.awcp.domain.Student;
import cn.org.awcp.repository.dao.StudentDao;
import cn.org.awcp.service.StudentService;
import cn.org.awcp.service.base.BaseServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends BaseServiceAdapter<Student,Integer> implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List query() {
        return studentDao.query("select * from test_student");
    }

    @Override
    public Object queryForObject(Integer id) {
        return studentDao.queryForObject("select name from test_student where id=?",id);
    }

    @Override
    public void executeUpdate(String name) {
        Map<String,Object> params=new HashMap<>(2);
        params.put("id",1);
        params.put("name",name);
        studentDao.executeUpdate("update test_student set name=:name where id=:id",params);
    }
}
