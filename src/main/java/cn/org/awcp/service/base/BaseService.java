package cn.org.awcp.service.base;


import cn.org.awcp.domain.base.IEntity;
import cn.org.awcp.controller.vo.RequestPage;
import cn.org.awcp.controller.vo.ResultPage;

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
