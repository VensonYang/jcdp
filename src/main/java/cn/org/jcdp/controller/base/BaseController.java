package cn.org.jcdp.controller.base;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseController
 *
 * @author venson
 * @version 20180703
 */
public abstract class BaseController {

   protected Map<String,Object> getQueryParam(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> param = new HashMap<>(parameterMap.size());
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            // 添加参数，取第一个value值
            param.put(entry.getKey(), entry.getValue()[0]);
        }
        return param;
    }



}
