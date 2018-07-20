package cn.org.jcdp.repository.base;

import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResultPage;
import cn.org.jcdp.domain.base.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BaseDao
 *
 * @author venson
 * @version 20180703
 */
@NoRepositoryBean
public interface BaseDao<Entity extends IEntity<ID>, ID extends Serializable> extends JpaRepository<Entity, ID> {


    /**
     * 保存对象<br/>
     * 注意：如果对象id是字符串，并且没有赋值，该方法将自动设置为uuid值
     *
     * @param entity 持久对象，或者对象集合
     */
    void store(Entity... entity);

    /**
     * 更新对象数据
     *
     * @param entity 持久对象，或者对象集合
     */
    void update(Entity... entity);

    /**
     * 执行查询语句(返回单个结果集)
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param values   queryStr中的?参数值,单个参数值或者多个参数值
     * @return 返回单个查询结果集
     */
    Object queryForObject(String queryStr, Object... values);

    /**
     * 执行查询语句(返回单个结果集)
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param params   key表示queryStr中参数变量名，value表示该参数变量值
     * @return 返回单个查询结果集
     */
    Object queryForObject(String queryStr, Map<String, Object> params);


    /**
     * 执行查询语句
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param values   queryStr中的?参数值,单个参数值或者多个参数值
     * @return 返回查询结果集
     */
    <T> List<T> query(String queryStr, Class<T> clazz, Object... values);

    /**
     * 执行查询语句
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param params   key表示queryStr中参数变量名，value表示该参数变量值
     * @return 返回查询结果集
     */
    <T> List<T> query(String queryStr, Class<T> clazz, Map<String, Object> params);

    /**
     * 执行查询语句
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param values   queryStr中的?参数值,单个参数值或者多个参数值
     * @return 返回查询结果集
     */
    List<Map> query(String queryStr, Object... values);

    /**
     * 执行查询语句
     *
     * @param queryStr 仅支持全部小写的本地SQL，如果不是全部小写的本地SQL则会抛异常
     * @param params   key表示queryStr中参数变量名，value表示该参数变量值
     * @return 返回查询结果集
     */
    List<Map> query(String queryStr, Map<String, Object> params);

    /**
     * 执行更新语句
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param values   queryStr中的?参数值,单个参数值或者多个参数值
     * @return 返回执行后受影响的数据个数
     */
    int executeUpdate(String queryStr, Object... values);

    /**
     * 执行更新语句
     *
     * @param queryStr 支持基于jpa标准的hql语句或本地SQL，如果是本地SQL则字符要全部小写
     * @param params   key表示queryStr中参数变量名，value表示该参数变量值
     * @return 返回执行后受影响的数据个数
     */
    int executeUpdate(String queryStr, Map<String, Object> params);


    /**
     * 分页查询
     *
     * @param pageable 分页实体类
     * @return ResultPage
     */
    ResultPage findAllByPage(RequestPage pageable);
}
