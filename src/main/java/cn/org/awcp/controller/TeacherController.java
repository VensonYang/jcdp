package cn.org.awcp.controller;

import cn.org.awcp.controller.base.CrudController;
import cn.org.awcp.domain.StudentClass;
import cn.org.awcp.domain.Teacher;
import cn.org.awcp.service.StudentClassService;
import cn.org.awcp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController extends CrudController<Teacher,Long> {

    @Autowired
    private TeacherService teacherService;

}
