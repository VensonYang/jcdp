package cn.org.jcdp;

import cn.org.jcdp.repository.base.BaseDaoFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
		basePackages={"cn.org.jcdp.repository"},
		repositoryFactoryBeanClass = BaseDaoFactoryBean.class
)
@ComponentScan(basePackages = {"cn.org.jcdp.controller","cn.org.jcdp.service","cn.org.jcdp.config"})
@EntityScan(basePackages = {"cn.org.jcdp.domain"})
@SpringBootApplication
public class JcdpApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcdpApplication.class, args);
	}
}
