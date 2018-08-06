package cn.org.jcdp.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Configuration{

    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .produces(new HashSet<>(Arrays.asList(new String[]{MediaType.APPLICATION_JSON_UTF8_VALUE})))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                    .title("jcdp api")
                    .description("api doc")
                    .termsOfServiceUrl("https://jcdp.org.cn网址链接")
                    .contact(new Contact("jcdp", "https://jcdp.org.cn", "jcdp@qq.com"))
                    .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> list= new ArrayList();
        list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list= new ArrayList();
        list.add( SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return list;
    }

    List<SecurityReference> defaultAuth() {
        List<SecurityReference> list= new ArrayList();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        list.add( new SecurityReference("Authorization", authorizationScopes));
        return list;
    }
}