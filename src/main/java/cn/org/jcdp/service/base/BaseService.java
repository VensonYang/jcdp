package cn.org.jcdp.service.base;


import cn.org.jcdp.domain.base.IEntity;
import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResultPage;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author venson
 * @version 20180703
 */
public interface BaseService<T extends IEntity<ID>,ID extends Serializable>{

    <S extends T> T save(S s);

    <S extends T> T update(ID id,S s);

    T findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    ResultPage findAllByPage(RequestPage page);

}
