package cn.org.jcdp.controller;

import cn.org.jcdp.controller.base.CrudController;
import cn.org.jcdp.controller.vo.ResponseResult;
import cn.org.jcdp.domain.Student;
import cn.org.jcdp.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController extends CrudController<Student,Integer> {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {
        ModelAndView mv = new ModelAndView("/student/list");
        List result = studentService.query();
        model.addAttribute("students", result);
        return mv;
    }

    @ApiOperation("executeUpdate")
    @GetMapping(value = "/getById")
    public ResponseResult getById(Integer id) {
        Serializable result=(Serializable)studentService.queryForObject(id);
        return ResponseResult.success(result);
    }

    @ApiOperation("executeUpdate")
    @PutMapping(value = "/executeUpdate")
    public ResponseResult executeUpdate(String name) {
        studentService.executeUpdate(name);
        return ResponseResult.blank();
    }


}
