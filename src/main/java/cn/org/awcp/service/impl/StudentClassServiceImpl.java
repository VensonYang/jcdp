package cn.org.awcp.service.impl;

import cn.org.awcp.domain.StudentClass;
import cn.org.awcp.repository.dao.StudentClassDao;
import cn.org.awcp.service.StudentClassService;
import cn.org.awcp.service.base.BaseServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentClassServiceImpl extends BaseServiceAdapter<StudentClass, String> implements StudentClassService {

    @Autowired
    private StudentClassDao studentClassDao;

}
