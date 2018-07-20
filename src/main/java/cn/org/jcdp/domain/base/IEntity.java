package cn.org.jcdp.domain.base;

import java.io.Serializable;
/**
 * IEntity
 *
 * @author venson
 * @version 20180703
 */
public interface IEntity<ID extends Serializable> extends Serializable {

    ID getId();

    void setId(ID id);
}
