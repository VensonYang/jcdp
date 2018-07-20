package cn.org.jcdp.service.impl;

import cn.org.jcdp.domain.Teacher;
import cn.org.jcdp.repository.dao.TeacherDao;
import cn.org.jcdp.service.TeacherService;
import cn.org.jcdp.service.base.BaseServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceAdapter<Teacher, Long> implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

}
