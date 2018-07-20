package cn.org.jcdp.service.impl;

import cn.org.jcdp.domain.StudentClass;
import cn.org.jcdp.repository.dao.StudentClassDao;
import cn.org.jcdp.service.StudentClassService;
import cn.org.jcdp.service.base.BaseServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentClassServiceImpl extends BaseServiceAdapter<StudentClass, String> implements StudentClassService {

    @Autowired
    private StudentClassDao studentClassDao;

}
