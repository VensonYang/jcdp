package cn.org.jcdp.service.base;


import cn.org.jcdp.domain.base.IEntity;
import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResultPage;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * BaseService
 *
 * @author venson
 * @version 20180703
 */
public interface BaseService<Entity extends IEntity<ID>,ID extends Serializable>{

    <S extends Entity> Entity save(S s);

    <S extends Entity> Optional<Entity> update(ID id, S s);

    <S extends Entity> void update(S s,Entity entity);

    Optional<Entity> findById(ID id);

    List<Entity> findAll();

    void delete(Entity entity);

    void deleteById(ID id);

    ResultPage findAllByPage(RequestPage page);

}
