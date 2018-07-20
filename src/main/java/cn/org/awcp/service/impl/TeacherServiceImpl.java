package cn.org.awcp.service.impl;

import cn.org.awcp.domain.Teacher;
import cn.org.awcp.repository.dao.TeacherDao;
import cn.org.awcp.service.TeacherService;
import cn.org.awcp.service.base.BaseServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceAdapter<Teacher, Long> implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

}
