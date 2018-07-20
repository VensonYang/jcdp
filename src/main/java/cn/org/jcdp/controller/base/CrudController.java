package cn.org.jcdp.controller.base;

import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResponseResult;
import cn.org.jcdp.domain.base.BaseEntity;
import cn.org.jcdp.domain.base.IEntity;
import cn.org.jcdp.service.base.BaseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

/**
 * CrudController
 *
 * @author venson
 * @version 20180703
 */
public abstract class CrudController<T extends IEntity<ID>, ID extends Serializable> extends BaseController {

    @Autowired
    private BaseService<T,ID> service;

    @ApiOperation("分页查询")
    @GetMapping(value = "")
    public ResponseResult page(RequestPage page,HttpServletRequest request) {
        page.setQueryParam(getQueryParam(request));
        return ResponseResult.success(service.findAllByPage(page));
    }

    @ApiOperation("新增")
    @PostMapping(value = "",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseResult add(T vo) {
        return ResponseResult.success(service.save(vo));
    }

    @ApiOperation("修改")
    @PutMapping(value ="/{id}")
    public ResponseResult update(@PathVariable("id") ID id,T vo) {
        return ResponseResult.success(service.update(id, vo));
    }

    @ApiOperation("获取")
    @GetMapping("/{id}")
    public ResponseResult get(@PathVariable("id") ID id) {
        return ResponseResult.success(service.findById(id));
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") ID id) {
        service.deleteById(id);
        return ResponseResult.success();
    }

}
