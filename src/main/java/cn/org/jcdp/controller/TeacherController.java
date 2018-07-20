package cn.org.jcdp.controller;

import cn.org.jcdp.controller.base.CrudController;
import cn.org.jcdp.domain.StudentClass;
import cn.org.jcdp.domain.Teacher;
import cn.org.jcdp.service.StudentClassService;
import cn.org.jcdp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController extends CrudController<Teacher,Long> {

    @Autowired
    private TeacherService teacherService;

}
