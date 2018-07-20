package cn.org.awcp.controller;

import cn.org.awcp.controller.base.CrudController;
import cn.org.awcp.domain.Student;
import cn.org.awcp.domain.StudentClass;
import cn.org.awcp.service.StudentClassService;
import cn.org.awcp.service.StudentService;
import cn.org.awcp.service.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student/class")
public class StudentClassController extends CrudController<StudentClass,String> {

    @Autowired
    private StudentClassService studentClassService;

}
