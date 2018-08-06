package cn.org.jcdp.service.base;

import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResultPage;
import cn.org.jcdp.domain.base.IEntity;
import cn.org.jcdp.repository.base.BaseDao;
import cn.org.jcdp.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * BaseServiceAdapter
 *
 * @author venson
 * @version 20180703
 */
public abstract class BaseServiceAdapter<Entity extends IEntity<ID>, ID extends Serializable> implements BaseService<Entity, ID> {


    @Autowired
    protected BaseDao<Entity, ID> baseDao;

    @Override
    public <S extends Entity> Entity save(S s) {
        baseDao.store(s);
        return s;
    }

    @Override
    public <S extends Entity> Optional<Entity> update(ID id, S s) {
        Optional<Entity> optional = findById(id);
        optional.ifPresent(entity -> update(s, entity));
        return optional;
    }

    @Override
    public <S extends Entity> void update(S s, Entity entity) {
        BeanUtils.copyProperties(s, entity);
        baseDao.update(entity);
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return baseDao.findById(id);
    }

    @Override
    public List<Entity> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void delete(Entity entity) {
        baseDao.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(entity-> delete(entity));
    }

    @Override
    public ResultPage findAllByPage(RequestPage page) {
        return baseDao.findAllByPage(page);
    }

}
