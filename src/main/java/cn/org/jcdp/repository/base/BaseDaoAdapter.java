package cn.org.jcdp.repository.base;

import cn.org.jcdp.common.exception.PlatFormException;
import cn.org.jcdp.common.utils.SnowflakeIdWorker;
import cn.org.jcdp.controller.vo.RequestPage;
import cn.org.jcdp.controller.vo.ResultPage;
import cn.org.jcdp.domain.base.BaseEntity;
import cn.org.jcdp.domain.base.IEntity;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BaseDaoAdapter
 *
 * @author venson
 * @version 20180703
 */
public class BaseDaoAdapter<Entity extends IEntity<ID>, ID extends Serializable> extends SimpleJpaRepository<Entity, ID> implements BaseDao<Entity, ID> {

    /**
     * 持久化上下文
     */
    private final EntityManager entityManager;

    public BaseDaoAdapter(Class<Entity> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void store(Entity... entity) {
        Assert.notNull(entity, "entity can not be null");
        for (Entity e : entity) {
            innerSave(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Entity... entity) {
        Assert.notNull(entity, "entity can not be null");
        for (Entity e : entity) {
            entityManager.merge(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int executeUpdate(String queryStr, Object... values) {
        Query query = getQuery(queryStr);
        setParameter(query, values);
        return query.executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int executeUpdate(String queryStr, Map<String, Object> params) {
        Query query = getQuery(queryStr);
        setParameter(query, params);
        return query.executeUpdate();
    }

    @Override
    public Object queryForObject(String queryStr, Object... values) {
        Query query = getQuery(queryStr);
        setParameter(query, values);
        return query.getSingleResult();
    }

    @Override
    public Object queryForObject(String queryStr, Map<String, Object> params) {
        Query query = getQuery(queryStr);
        setParameter(query, params);
        return query.getSingleResult();
    }

    @Override
    public <T> List<T> query(String queryStr, Class<T> clazz, Object... values) {
        Assert.notNull(clazz,"clazz类型不能为空");
        Query query=getQuery(queryStr, clazz);
        setParameter(query, values);
        return query.getResultList();
    }

    @Override
    public List<Map> query(String queryStr, Object... values) {
        return query(queryStr, Map.class, values);
    }

    @Override
    public <T> List<T> query(String queryStr, Class<T> clazz, Map<String, Object> params) {
        Assert.notNull(clazz,"clazz类型不能为空");
        Query query=getQuery(queryStr, clazz);
        setParameter(query, params);
        return query.getResultList();
    }

    @Override
    public List<Map> query(String queryStr, Map<String, Object> params) {
        return query(queryStr, Map.class, params);
    }

    @Override
    public ResultPage findAllByPage(RequestPage requestPage) {
        //分页信息
        Pageable pageable = PageRequest.of(requestPage.getPage() - 1, requestPage.getLimit(), requestPage.getSortDirection(), requestPage.getSorts());
        //查询
        Page page = this.findAll(where(requestPage.getEntity()), pageable);
        return ResultPage.of(page.getContent(), page.getTotalElements(), requestPage.getOffset(), requestPage.getLimit());
    }

    /**
     * 设置参数
     *
     * @param query  查询对象
     * @param params 具名参数键值对
     */
    private void setParameter(Query query, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String name : params.keySet()) {
                query.setParameter(name, params.get(name));
            }
        }
    }

    /**
     * 设置参数
     *
     * @param query  查询对象
     * @param values 参数值
     */
    private void setParameter(Query query, Object[] values) {
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
    }

    /**
     * 如果全部是小写，如果是则属于本地SQL
     *
     * @param queryStr 查询语句
     * @return
     */
    private Query getQuery(String queryStr) {
        return getQuery(queryStr, null);
    }

    /**
     * 如果全部是小写，如果是则属于本地SQL
     *
     * @param queryStr 查询语句
     * @return
     */
    private Query getQuery(String queryStr, Class<?> type) {
        Query query;
        if (isLower(queryStr)) {
            if (type == null) {
                query = entityManager.createNativeQuery(queryStr);
            } else {
                if(type==Map.class){
                    query  = entityManager.createNativeQuery(queryStr);
                    query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                }else{
                    query  = entityManager.createNativeQuery(queryStr, type);

                }
            }
        } else {
            if (type == null) {
                query = entityManager.createQuery(queryStr);
            } else {
                query = entityManager.createQuery(queryStr, type);
            }
        }
        return query;
    }

    /**
     * 判断字符串是否全部是小写
     *
     * @param str
     * @return
     */
    private boolean isLower(String str) {
        String newStr = str.toLowerCase();
        return newStr.equals(str);
    }

    private final static String BASE_STRING_ID_PREFIX = "ID_";

    /**
     * 保存对象
     *
     * @param entity 保存对象
     * @return 主键
     */
    private void innerSave(Entity entity) {
        Assert.notNull(entity, "entity can not be null");
        ID id = entity.getId();
        if (id == null) {
            Class<ID> idClass = getIdClass();
            // 字符串UUID
            if (idClass == String.class) {
                id = (ID) (BASE_STRING_ID_PREFIX + SnowflakeIdWorker.getDefault().nextId());
                entity.setId(id);
                //分布式数值ID
            } else if (idClass == Long.class) {
                id = (ID) Long.valueOf(SnowflakeIdWorker.getDefault().nextId());
                entity.setId(id);
                //数据库自增ID
            } else if (idClass == Integer.class) {
                Field idField = getIdField();
                if (idField == null) {
                    throw new PlatFormException("主键未设置");
                }
                if (!idField.isAnnotationPresent(GeneratedValue.class)) {
                    throw new PlatFormException("主键未设置策略：GeneratedValue");
                }
                GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
                GenerationType generationType = generatedValue.strategy();
                if (generationType == GenerationType.SEQUENCE || generationType == GenerationType.TABLE) {
                    throw new PlatFormException("主键策略类型：GenerationType请设置为:AUTO或IDENTITY");
                }
            } else {
                throw new PlatFormException("不支持的主键类型：" + idClass.getName());
            }
        }
        entityManager.persist(entity);
        entityManager.flush();
    }

    private Field getIdField() {
        for (Class searchType = getDomainClass(); Object.class != searchType && searchType != null; searchType = searchType.getSuperclass()) {
            Field[] fields = searchType.getDeclaredFields();
            int len = fields.length;
            for (int i = 0; i < len; ++i) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Id.class)) {
                    return field;
                }
            }
        }
        return null;
    }


    /**
     * 获取主键Class对象
     *
     * @return 主键Class对象
     */
    private Class<ID> getIdClass() {
        Type type;
        if (getDomainClass().getSuperclass() == BaseEntity.class) {
            type = getDomainClass().getGenericSuperclass();
        } else if (getDomainClass().getInterfaces()[0] == IEntity.class) {
            type = getDomainClass().getGenericInterfaces()[0];
        } else {
            throw new IllegalArgumentException("实体类必须继承BaseEntity抽象类或实现IEntity接口");
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return (Class<ID>) parameterizedType.getActualTypeArguments()[0];
    }


    /**
     * 自动构建分页条件查询
     *
     * @param entity 查询实体
     */
    private Specification<Entity> where(Object entity) {
        return (Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (entity!=null) {
                for(Class searchType = entity.getClass();  Object.class != searchType; searchType = searchType.getSuperclass()) {
                    Field[] fields = searchType.getDeclaredFields();
                    for(int i=fields.length-1;i>-1;i--){
                        Field field = fields[i];
                        if(Modifier.isStatic(field.getModifiers())){
                            continue;
                        }
                        field.setAccessible(true);
                        Object value = ReflectionUtils.getField(field, entity);
                        if(StringUtils.isEmpty(value)){
                            continue;
                        }
                        String key=field.getName();
                        String lowerKey = key.toLowerCase();
                        if (field.isAnnotationPresent(Id.class)) {
                            Predicate equal = cb.equal(root.get(key).as(getIdClass()), value);
                            predicates.add(equal);
                        } else if (lowerKey.contains("name") || lowerKey.contains("title")) {
                            Predicate like = cb.like(root.get(key).as(String.class), value + "%");
                            predicates.add(like);
                        } else {
                            Predicate equal = cb.equal(root.get(key).as(field.getType()), value);
                            predicates.add(equal);
                        }
                    }
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
