package cn.org.awcp.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * RequestPage
 *
 * @author venson
 * @version 20180703
 */
public class RequestPage implements Serializable{

    private static final long serialVersionUID = 2138081960508489397L;
    private int limit = 10;
    private int offset = 0;
    private String sort;
    private String direction;
    private Map<String,Object> queryParam;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setQueryParam(Map<String, Object> queryParam) {
        this.queryParam = queryParam;
    }

    @ApiModelProperty(hidden = true)
    public Map<String, Object> getQueryParam() {
        return queryParam;
    }

    @ApiModelProperty(hidden = true)
    public int getPage() {
        return (this.limit + offset) / limit;
    }

    @ApiModelProperty(hidden = true)
    public String[] getSorts() {
        if (StringUtils.isEmpty(getSort())) {
            return new String[]{"id"};
        }
        return sort.split(",");
    }

    @ApiModelProperty(hidden = true)
    public Sort.Direction getSortDirection() {
        if (Sort.Direction.DESC.name().equals(getDirection())) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }




}
