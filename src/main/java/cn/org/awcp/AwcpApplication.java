package cn.org.awcp;

import cn.org.awcp.repository.base.BaseDaoFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
		basePackages={"cn.org.awcp.repository"},
		repositoryFactoryBeanClass = BaseDaoFactoryBean.class
)
@ComponentScan(basePackages = {"cn.org.awcp.controller","cn.org.awcp.service","cn.org.awcp.config"})
@EntityScan(basePackages = {"cn.org.awcp.domain"})
@SpringBootApplication
public class AwcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwcpApplication.class, args);
	}
}
