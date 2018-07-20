package cn.org.jcdp.interceptor;

import cn.org.jcdp.controller.vo.ResponseResult;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommInterceptor
 *
 * @author venson
 * @version 20180703
 */
public class CommInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ResponseResult.remove();
    }
}
