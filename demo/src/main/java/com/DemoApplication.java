package com;

import javax.servlet.MultipartConfigElement;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * SpringBootApplication注解
 * 
 * @author Chongyang
 */
@MapperScan("com.example.mapper")
//启动时默认不连接数据库
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ServletComponentScan // 使用注解的方式注册servlet需要在SpringbootHelloApplication.java中添加@ServletComponentScan注解
@EnableScheduling   //开启定时器
public class DemoApplication {

	/*
	 * @Bean public ServletRegistrationBean servletRegistrationBean() { return new
	 * ServletRegistrationBean(new Servlet1(), "/servlet/*");//
	 * ServletName默认值为首字母小写，即servlet }
	 */
	@Autowired
    private MultipartConfigElement multipartConfigElement;//注入 “多部件配置元素”
	

	/**
	 * 修改DispatcherServlet默认配置
	 *
	 * @param dispatcherServlet
	 * @author Chongyang
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		//这里需要将附件配置设置进去，否则请求不过来 报异常：Required request part 'file' is not present
        registration.setMultipartConfig(multipartConfigElement); 
		registration.addUrlMappings("*.action"); // 只有*.action 的请求能通过
		registration.addUrlMappings("*.json");
		return registration;
	}
	
	//DataSource配置
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
