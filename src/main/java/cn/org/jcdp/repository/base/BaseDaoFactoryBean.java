package cn.org.jcdp.repository.base;

import cn.org.jcdp.domain.base.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * BaseDaoFactoryBean
 *
 * @author venson
 * @version 20180703
 */
public class BaseDaoFactoryBean<R extends JpaRepository<T, I>, T,
        I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    public BaseDaoFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }
 
    //创建一个内部类，该类不用在外部访问
    private static class BaseRepositoryFactory<T extends IEntity<I>, I extends Serializable>
            extends JpaRepositoryFactory {
 
        private final EntityManager em;
 
        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }
 
        //设置具体的实现类是BaseRepositoryImpl
        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseDaoAdapter<T,I>((Class<T>) information.getDomainType(), em);
        }
 
        //设置具体的实现类的class
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseDao.class;
        }
    }}
