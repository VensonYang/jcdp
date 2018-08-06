package cn.org.jcdp.config;

import cn.org.jcdp.interceptor.CommInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * WebConfig
 *
 * @author venson
 * @version 20180703
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommInterceptor());
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //序列换成json时,将所有的long变成string
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter=(MappingJackson2HttpMessageConverter)converters.get(7);
        ObjectMapper objectMapper=jackson2HttpMessageConverter.getObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        objectMapper.getRegisteredModuleIds();
    }



    @Override
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
}
