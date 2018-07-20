package cn.org.awcp.domain.base;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * BaseEntity
 *
 * @author venson
 * @version 20180703
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements IEntity<ID> {

    @Id
    @ApiModelProperty(hidden = true)
    protected ID id;


    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}
