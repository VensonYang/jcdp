package cn.org.jcdp.controller;

import cn.org.jcdp.controller.base.CrudController;
import cn.org.jcdp.domain.Student;
import cn.org.jcdp.domain.StudentClass;
import cn.org.jcdp.service.StudentClassService;
import cn.org.jcdp.service.StudentService;
import cn.org.jcdp.service.base.BaseService;
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
