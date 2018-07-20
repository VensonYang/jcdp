package cn.org.awcp.service.base;

import cn.org.awcp.controller.vo.RequestPage;
import cn.org.awcp.controller.vo.ResultPage;
import cn.org.awcp.domain.base.IEntity;
import cn.org.awcp.repository.base.BaseDao;
import cn.org.awcp.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * BaseServiceAdapter
 *
 * @author venson
 * @version 20180703
 */
public abstract class BaseServiceAdapter<T extends IEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {


    @Autowired
    protected BaseDao<T, ID> baseDao;
    
    @Override
    public <S extends T> T save(S s) {
        baseDao.store(s);
        return s;
    }

    @Override
    public <S extends T> T update(ID id, S s) {
        return baseDao.findById(id).map(t -> {
            BeanUtils.copyProperties(s, t);
            baseDao.update(t);
            return t;
        }).orElse(null);
    }

    @Override
    public T findById(ID id) {
        return baseDao.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void deleteById(ID id) {
        baseDao.findById(id).ifPresent(s -> baseDao.delete(s));
    }

    @Override
    public ResultPage findAllByPage(RequestPage page) {
      return baseDao.findAllByPage(page);
    }

}
