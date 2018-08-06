package cn.org.jcdp.controller.base;

import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResponseResult;
import cn.org.jcdp.domain.base.IEntity;
import cn.org.jcdp.service.base.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * CrudController
 *
 * @author venson
 * @version 20180703
 */
public abstract class  CrudController<Entity extends IEntity<ID>, ID extends Serializable> extends BaseController {

    @Autowired
    private BaseService<Entity, ID> service;

    @ApiOperation("分页查询")
    @GetMapping("")
    @PreAuthorize("hasAuthority('read')")
    public ResponseResult page(RequestPage page,Entity entity) {
        page.setEntity(entity);
        return ResponseResult.success(service.findAllByPage(page));
    }

    @ApiOperation("新增")
    @PostMapping("")
    @PreAuthorize("hasAuthority('create')")
    public ResponseResult add(@RequestBody Entity entity) {
        return ResponseResult.success(service.save(entity), HttpStatus.CREATED);
    }

    @ApiOperation("修改")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseResult update(@PathVariable("id") ID id, @RequestBody Entity entity) {
        return service.findById(id).map(t -> {
            service.update(entity, t);
            return ResponseResult.success(t);
        }).orElse(ResponseResult.empty());
    }

    @ApiOperation("获取")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseResult get(@PathVariable("id") ID id) {
        return service.findById(id).map(t -> ResponseResult.success(t)).orElse(ResponseResult.empty());
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete')")
    public ResponseResult delete(@PathVariable("id") ID id) {
        return service.findById(id).map(t -> {
            service.delete(t);
            return ResponseResult.success(t);
        }).orElse(ResponseResult.empty());
    }

}
